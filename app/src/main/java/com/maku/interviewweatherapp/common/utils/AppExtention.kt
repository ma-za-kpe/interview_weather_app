package com.maku.interviewweatherapp.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*


fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun <T> Context.shareApp(it: Class<T>, extras: Bundle.() -> Unit = {}, text: Int) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    val shareIntent = Intent(Intent.ACTION_SEND)
    startActivity(Intent.createChooser(shareIntent, getString(text)))
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

//fun Context.materialDialog(title: String, message: String, toastMessage: String, positiveText: String, negativeText: String, activity: Activity, drawable: Int){
//    val mDialog = MaterialDialog.Builder(activity)
//        .setTitle(title)
//        .setMessage(message)
//        .setCancelable(false)
//        .setPositiveButton(
//            positiveText,drawable
//        ) { dialogInterface, which ->
//            // Delete Operation
//            toast(toastMessage)
//            dialogInterface.dismiss()
//        }
//        .setNegativeButton(
//            negativeText, drawable
//        ) { dialogInterface, which -> dialogInterface.dismiss() }
//        .build()
//
//    // Show Dialog
//
//    // Show Dialog
//    mDialog.show()
//}

/**
 * Returns [String] based on current time in "h:mm a". format
 */
fun getTime(hr: Int, min: Int): String? {
    val cal = Calendar.getInstance()
    cal[Calendar.HOUR_OF_DAY] = hr
    cal[Calendar.MINUTE] = min
    val formatter: Format
    formatter = SimpleDateFormat("h:mm a")
    return formatter.format(cal.time)
}
