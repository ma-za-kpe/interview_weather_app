package com.maku.interviewweatherapp.common.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maku.interviewweatherapp.R
import com.maku.interviewweatherapp.common.data.api.models.CityWeather
import com.maku.interviewweatherapp.common.utils.setImage
import com.maku.interviewweatherapp.databinding.RecyclerViewWeatherItemBinding


class WeatherAdapter internal constructor(
  private val setFavorite: (Any) -> Unit,
  private val viewDetails: (Any) -> Unit,
) :
  RecyclerView.Adapter<WeatherAdapter.ListViewHolder?>() {

  private var cityWeather = ArrayList<CityWeather>()

  class ListViewHolder(private val binding: RecyclerViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun setDashBoardItem(
      weatherItem: CityWeather,
      setFavorite: (Any) -> Unit,
      viewDetails: (Any) -> Unit,
    ){
      binding.result = weatherItem

      binding.name.text = weatherItem.name
      weatherItem.weather.forEach {
        binding.description.text = it.description
        // http://openweathermap.org/img/w/03n.png
        binding.icon.setImage("http://openweathermap.org/img/w/${it.icon}.png")
      }
      binding.temp.text = weatherItem.main.temp.toString()
      binding.date.text  = weatherItem.dt.toString()

      if(weatherItem.fav){
        binding.fav.setBackgroundResource(R.drawable.ic_favorite_solid)
      } else {
        binding.fav.setBackgroundResource(R.drawable.ic_favorite_outline)
      }

      binding.weatherCardItem.setOnClickListener {
        viewDetails(weatherItem)
      }

      binding.fav.setOnClickListener {
        setFavorite(weatherItem)
      }

    }


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

  override fun getItemCount(): Int {
    return cityWeather.size
  }

  fun setDataWhenFavEmpty(allCityWeather: List<CityWeather>){
    val recipesDiffUtil = WeatherDiffUtil(cityWeather, allCityWeather)
    val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
    cityWeather = allCityWeather as ArrayList<CityWeather>
    diffResult.dispatchUpdatesTo(this)
  }
  override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    holder.setDashBoardItem(
      cityWeather[position], setFavorite, viewDetails)
  }

}

// TODO: review this code before pushing
class WeatherDiffUtil<T>(
  private val oldList: List<T>,
  private val newList: List<T>,
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

// TODO: next time just deprecate this class instead of having such dirty code
//package com.maku.interviewweatherapp.common.presentation
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.maku.interviewweatherapp.R
//import com.maku.interviewweatherapp.common.data.api.models.CityWeather
//import com.maku.interviewweatherapp.common.data.cache.entities.FavoriteWeatherEntity
//import com.maku.interviewweatherapp.common.utils.setImage
//import com.maku.interviewweatherapp.databinding.RecyclerViewFavWeatherItemBinding
//import com.maku.interviewweatherapp.databinding.RecyclerViewWeatherItemBinding
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class WeatherAdapter internal constructor(
//  private val mContext: Context,
//  // private val favWeather: List<FavoriteWeatherEntity>,
//  private val setFavorite: (Any) -> Unit,
//  private val viewDetails: (Any) -> Unit,
//) :
//  RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
//
//  val VIEW_TYPE_FAV = 0
//  val VIEW_TYPE_OTHER = 1
//
//  private var cityWeather = ArrayList<CityWeather>()
//  private var favCityWeather = ArrayList<FavoriteWeatherEntity>()
//
//  class FavListViewHolder(private val binding: RecyclerViewFavWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
//
//    fun setDashBoardItem(
//      weatherItem: FavoriteWeatherEntity,
//      setFavorite: (Any) -> Unit,
//      viewDetails: (Any) -> Unit,
//      mContext: Context,
//      //favWeather: List<FavoriteWeatherEntity>
//    ){
//      binding.result = weatherItem
//
//      binding.name.text = weatherItem.favWeatherCity.name
//      weatherItem.favWeatherCity.weather.forEach {
//        binding.description.text = it.description
//        // http://openweathermap.org/img/w/03n.png
//        binding.icon.setImage("http://openweathermap.org/img/w/${it.icon}.png")
//      }
//      binding.temp.text = weatherItem.favWeatherCity.main.temp.toString()
//      binding.date.text  = weatherItem.favWeatherCity.dt.toString()
//
//      if (weatherItem.favWeatherCity.fav){
//        binding.fav.setBackgroundResource(R.drawable.ic_favorite_solid)
//      }
//
//      binding.weatherCardItem.setOnClickListener {
//        viewDetails(weatherItem)
//      }
//
//      binding.fav.setOnClickListener {
//        setFavorite(weatherItem)
//      }
//
//    }
//
//
////    private fun getDateTime(s: Int?): String? {
////      return try {
////          val sdf = SimpleDateFormat("MM/dd/yyyy")
////          val netDate = Date(s?.toLong()?.times(1000))
////        sdf.format(netDate)
////      } catch (e: Exception) {
////        e.toString()
////      }
////    }
//
//    companion object{
//      fun from(parent: ViewGroup): FavListViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = RecyclerViewFavWeatherItemBinding.inflate(layoutInflater, parent, false)
//        return FavListViewHolder(binding)
//      }
//    }
//  }
//
//  class ListViewHolder(private val binding: RecyclerViewWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
//
//    fun setDashBoardItem(
//      weatherItem: CityWeather,
//      setFavorite: (Any) -> Unit,
//      viewDetails: (Any) -> Unit,
//      mContext: Context,
//      //favWeather: List<FavoriteWeatherEntity>
//    ){
//      binding.result = weatherItem
//
//      binding.name.text = weatherItem.name
//      weatherItem.weather.forEach {
//        binding.description.text = it.description
//        // http://openweathermap.org/img/w/03n.png
//        binding.icon.setImage("http://openweathermap.org/img/w/${it.icon}.png")
//      }
//      binding.temp.text = weatherItem.main.temp.toString()
//      binding.date.text  = weatherItem.dt.toString()
//
//      if (weatherItem.fav){
//        binding.fav.setBackgroundResource(R.drawable.ic_favorite_solid)
//      }
//
//      binding.weatherCardItem.setOnClickListener {
//        viewDetails(weatherItem)
//      }
//
//      binding.fav.setOnClickListener {
//        setFavorite(weatherItem)
//      }
//
//    }
//
//
////    private fun getDateTime(s: Int?): String? {
////      return try {
////          val sdf = SimpleDateFormat("MM/dd/yyyy")
////          val netDate = Date(s?.toLong()?.times(1000))
////        sdf.format(netDate)
////      } catch (e: Exception) {
////        e.toString()
////      }
////    }
//
//    companion object{
//      fun from(parent: ViewGroup): ListViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = RecyclerViewWeatherItemBinding.inflate(layoutInflater, parent, false)
//        return ListViewHolder(binding)
//      }
//    }
//  }
//
////  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
////     return ListViewHolder.from(parent)
////  }
//
//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//    val viewHolder: RecyclerView.ViewHolder? = when (viewType) {
//      VIEW_TYPE_FAV -> {
//        FavListViewHolder.from(parent)
//      }
//      VIEW_TYPE_OTHER -> {
//        ListViewHolder.from(parent)
//      }
//      else -> null
//    }
//    return viewHolder!!
//  }
//
//  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//    if (holder is FavListViewHolder) {
//      holder.setDashBoardItem(
//        favCityWeather[position], setFavorite, viewDetails,
//        mContext,
//        //favWeather
//      )    } else  if (holder is ListViewHolder) {
//      holder.setDashBoardItem(
//        cityWeather[position], setFavorite, viewDetails,
//        mContext,
//        //favWeather
//      )
//      }
//  }
//
//  override fun getItemCount(): Int {
//    return favCityWeather.size + cityWeather.size
//  }
//
//  override fun getItemViewType(position: Int): Int {
//    if (position < favCityWeather.size) {
//      return VIEW_TYPE_FAV
//    }
//    return if (position - favCityWeather.size < cityWeather.size) {
//      VIEW_TYPE_OTHER
//    } else -1
//  }
//
//  fun setDataWhenFavEmpty(allCityWeather: List<CityWeather>){
//    val recipesDiffUtil = WeatherDiffUtil(cityWeather, allCityWeather)
//    val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
//    cityWeather = allCityWeather as ArrayList<CityWeather>
//    diffResult.dispatchUpdatesTo(this)
//  }
//
//  fun setDataWhenFav(allCityWeather: List<FavoriteWeatherEntity>){
//    val recipesDiffUtil = WeatherDiffUtil(cityWeather, favCityWeather)
//    val diffResult = DiffUtil.calculateDiff(recipesDiffUtil)
//    favCityWeather = allCityWeather as ArrayList<FavoriteWeatherEntity>
//    diffResult.dispatchUpdatesTo(this)
//  }
//
//  // TODO: use diff util please
//  fun setData(allCityWeather: List<CityWeather>, allFavCityWeather: List<FavoriteWeatherEntity>) {
////    cityWeather.clear()
////    cityWeather.addAll(allCityWeather as ArrayList)
////
////    favCityWeather.clear()
////    favCityWeather.addAll(allFavCityWeather as ArrayList)
//
//  }
//
////  fun setDataWhenFavEmpty(allCityWeather: List<CityWeather>) {
////    cityWeather.clear()
////    cityWeather.addAll(allCityWeather as ArrayList)
////
//////    favCityWeather.clear()
//////    favCityWeather.addAll(allFavCityWeather as ArrayList)
////  }
//
//}
//
//interface Equatable {
//  override fun equals(other: Any?): Boolean
//}
//
//// TODO: review this code before pushing
//class WeatherDiffUtil<T>(
//  private val oldList: List<T>,
//  private val newList: List<T>,
//): DiffUtil.Callback() {
//  override fun getOldListSize(): Int {
//    return oldList.size
//  }
//
//  override fun getNewListSize(): Int {
//    return newList.size
//  }
//
//  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//    return oldList[oldItemPosition] === newList[newItemPosition]
//  }
//
//  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//    return oldList[oldItemPosition] == newList[newItemPosition]
//  }
//}
