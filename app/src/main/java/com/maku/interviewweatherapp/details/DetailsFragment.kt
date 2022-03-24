package com.maku.interviewweatherapp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maku.interviewweatherapp.R
import com.maku.interviewweatherapp.common.utils.setImage
import com.maku.interviewweatherapp.common.vm.SharedViewModel
import com.maku.interviewweatherapp.databinding.FragmentDetailsBinding
import com.maku.interviewweatherapp.databinding.FragmentHomeBinding


class DetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.allSelectedWeather.observe(viewLifecycleOwner, {
            binding.name.text = it.name
            binding.date.text = it.dt.toString()
            binding.name.text = it.name
            binding.temp.text = it.main.temp.toString()
            it.weather.forEach {it ->
                binding.description.text = it.description
                binding.icon.setImage("http://openweathermap.org/img/w/${it.icon}.png")
            }
            binding.wind.text = it.wind.speed.toString()
            binding.humid.text = it.main.humidity.toString()
            binding.visibility.text = it.visibility.toString()
            binding.pressure.text = it.main.pressure.toString()
            binding.dewpoint.text = it.main.feelsLike.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}