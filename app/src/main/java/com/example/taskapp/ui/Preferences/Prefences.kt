package com.example.taskapp.ui.Preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class Preferences(private val context: Context) {
    val sharedPref: SharedPreferences = context.getSharedPreferences("prefences",MODE_PRIVATE)


    fun isBoardingShowed():Boolean{
        return sharedPref.getBoolean("board",false)
    }

    fun setBoardingShowed(isSnow:Boolean){
        sharedPref.edit().putBoolean("board",isSnow).apply()
    }
    fun setProfile(isSnow: String) {
        sharedPref.edit().putString("image", isSnow).apply()
    }

    fun isProfile():String{
        return sharedPref.getString("image","").toString()
    }


}

