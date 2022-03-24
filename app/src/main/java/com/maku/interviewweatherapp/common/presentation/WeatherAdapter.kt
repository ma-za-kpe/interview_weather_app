package com.maku.interviewweatherapp.common.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maku.interviewweatherapp.R
import com.maku.interviewweatherapp.common.data.cache.entities.CacheWeather
import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
import com.maku.interviewweatherapp.common.logging.Logger
import com.maku.interviewweatherapp.databinding.RecyclerViewWeatherItemBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter internal constructor(
  private val mContext: Context,
  private val favWeather: List<FavoriteWeatherEntity>,
  private val setFavorite: (Any) -> Unit,
  private val viewDetails: (Any) -> Unit,
) :
  RecyclerView.Adapter<WeatherAdapter.ListViewHolder>() {

  private var cityWeather = emptyList<CacheWeather>()

  class ListViewHolder(private val binding: RecyclerViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setDashBoardItem(
      weatherItem: CacheWeather,
      setFavorite: (Any) -> Unit,
      viewDetails: (Any) -> Unit,
      mContext: Context,
      favWeather: List<FavoriteWeatherEntity>
    ){
      binding.result = weatherItem

      binding.name.text = weatherItem.country
//      weatherItem.weather.toList().forEach {
//        binding.description.text = it.description
//        // http://openweathermap.org/img/w/03n.png
//        binding.icon.setImage("http://openweathermap.org/img/w/${it.icon}.png")
//      }
      binding.temp.text = weatherItem.temp.toString()
      binding.date.text  = weatherItem.dt.toString()

      binding.weatherCardItem.setOnClickListener {
        viewDetails(weatherItem)
      }

      // not advised !!!
      if (favWeather.isNotEmpty()){
        Logger.d("favorite city $favWeather")
        for (c in favWeather){
          if (c.id == weatherItem.id){
             binding.fav.setBackgroundResource(R.drawable.ic_favorite_solid)
          }
        }
      }

      binding.fav.setOnClickListener {
        setFavorite(weatherItem)
      }

    }


//    private fun getDateTime(s: Int?): String? {
//      return try {
//          val sdf = SimpleDateFormat("MM/dd/yyyy")
//          val netDate = Date(s?.toLong()?.times(1000))
//        sdf.format(netDate)
//      } catch (e: Exception) {
//        e.toString()
//      }
//    }

    companion object{
      fun from(parent: ViewGroup): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewWeatherItemBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(binding)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
    return ListViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    holder.setDashBoardItem(cityWeather[position], setFavorite, viewDetails,
      mContext,
      favWeather
    )
  }

  override fun getItemCount(): Int {
    return cityWeather.size
  }

  fun setData(allCityWeather: List<CacheWeather>){
    val recipesDiffUtil = WeatherDiffUtil(cityWeather, allCityWeather)
    val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
    cityWeather = allCityWeather
    diffResult.dispatchUpdatesTo(this)
  }

}

// TODO: review this code before pushing
class WeatherDiffUtil<T>(
  private val oldList: List<T>,
  private val newList: List<T>
): DiffUtil.Callback() {
  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] === newList[newItemPosition]
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] == newList[newItemPosition]
  }
}
