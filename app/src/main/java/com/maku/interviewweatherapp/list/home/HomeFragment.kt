package com.maku.interviewweatherapp.list.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.maku.interviewweatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        private const val ITEMS_PER_ROW = 2
    }

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
//        setupUI()
    }

//    private fun setupUI() {
//        val adapter = createAdapter() // 3
//        setupRecyclerView(adapter)
//    }
//    private fun createAdapter(): WeatherAdapter {
//        return WeatherAdapter()
//    }
//    private fun setupRecyclerView(weatherAdapter:
//                                  WeatherAdapter) {
//        binding.weatherRecyclerView.apply {
//            adapter = weatherAdapter
//            layoutManager = GridLayoutManager(requireContext(),
//                ITEMS_PER_ROW)
//            setHasFixedSize(true)
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}