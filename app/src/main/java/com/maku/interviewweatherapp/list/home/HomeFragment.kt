package com.maku.interviewweatherapp.list.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.maku.interviewweatherapp.R
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.logging.Logger.d
import com.maku.interviewweatherapp.common.presentation.WeatherAdapter
import com.maku.interviewweatherapp.common.utils.NetworkResult
import com.maku.interviewweatherapp.common.utils.toast
import com.maku.interviewweatherapp.common.vm.SharedViewModel
import com.maku.interviewweatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object {
        private const val ITEMS_PER_ROW = 1
    }

//    private val workManager by lazy {
//        WorkManager.getInstance(applicationContext)
//    }

    private var allCityWeatherData: List<CityWeather>? = null

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: SharedViewModel by activityViewModels()

    private val mAdapter by lazy {
        WeatherAdapter( {
            favoriteWeather(it as CityWeather)
        }, {
            viewDetails(it as CityWeather)
        })
    }

    private fun viewDetails(cityWeather: CityWeather) {
        // open bottom dialog
        model.selectWeather(cityWeather)
        findNavController().navigate(R.id.action_navigation_home_to_detailsFragment)
    }

    // TODO: find better way to do this, either fav through details fragment, or domain modelling
    private fun favoriteWeather(cityWeather: CityWeather) {
        homeViewModel.favWeather(true, cityWeather.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readLocalWeathereData()
        setupUI()
    }

    fun createPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()

        // TODO: dont forget to tweak this to fit project description
        val refreshWork =
            PeriodicWorkRequest.Builder(
                RefreshDbWorker::class.java,
                15, // repeating interval
                TimeUnit.HOURS,
                5, // flex interval - worker will run some when within this period of time, but at the end of repeating interval
                TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
            "periodicImageDownload",
            ExistingPeriodicWorkPolicy.KEEP,
            refreshWork
        )
    }

    private fun setupUI() {
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    private fun readLocalWeathereData() {
        lifecycleScope.launch {
            homeViewModel.readAllWeatherData.observe(viewLifecycleOwner, { localData ->
                if (localData.isNotEmpty()) {
                    d("data: all city  ${localData.size}")
                    allCityWeatherData = localData
                    mAdapter.setDataWhenFavEmpty(localData)
                } else {
                    requestApiData()
                }
            })
        }
    }

    private fun requestApiData(){
        homeViewModel.getWeatherData()
        homeViewModel.weatherResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data.let {
                        it?.list?.let { it1 -> mAdapter.setDataWhenFavEmpty(it1) }
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    // comment because we only load data once, according to the instructions and also
                    // because we don't let users refresh for any new data
                    // loadDataFromCache()
                    requireActivity().toast(response.message.toString())
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    requireActivity().toast("Loading data ... ")
                }
            }

        })
    }

    private fun setupRecyclerView() {
        binding.weatherRecyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(),
                ITEMS_PER_ROW)
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        searchView!!.setOnCloseListener {
            mAdapter.setDataWhenFavEmpty(allCityWeatherData!!)
            false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            d("searching")
            searchDbData(query)
        }
        return true
    }

    // this method is not good for performance, its expensive through its calls to the API
    // although if used with local db can be good, or adding delay to the e.g. flowable debounce
    // use wisely
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    // in this case we shall only work with the local db if its not empyt
    fun searchDbData(search_term: String?){
        // 0. check list/dataset size
        // 1. implement search logic() same filter loic as above
        if (allCityWeatherData?.size!! > 0) {
            mAdapter.setDataWhenFavEmpty(filterResults(allCityWeatherData!!, search_term!!))
        } else {
            // this is where you can query the remote db, although this logic is not necessary
                // according to the scope of the project
            Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show()
            binding.nodata.visibility = View.VISIBLE
        }

        // 2. update the recyclerview as needed
    }

    private fun filterResults(result_items: List<CityWeather>, term: String): List<CityWeather> {
        return result_items.filter { it.name.lowercase() == term.lowercase().trim() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Having an Adapter as a property of a Fragment is a known way of leaking the
        //RecyclerView.
        binding.weatherRecyclerView.adapter = null
        _binding = null
    }

}