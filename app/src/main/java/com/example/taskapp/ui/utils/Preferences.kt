package com.example.taskapp.ui.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(private val context: Context) {
   private val sharedPref: SharedPreferences = context.getSharedPreferences("presences",MODE_PRIVATE)


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
    fun setProfileUser(isSnow: String) {
        sharedPref.edit().putString("username", isSnow).apply()
    }

    fun isProfileUser():String{
        return sharedPref.getString("username","").toString()
    }

    fun setProfileEmail(isSnow: String) {
        sharedPref.edit().putString("email", isSnow).apply()
    }

    fun isProfileEmail():String{
        return sharedPref.getString("email","").toString()
    }

    fun setProfilePhone(isSnow: String) {
        sharedPref.edit().putString("phone", isSnow).apply()
    }

    fun isProfilePhone():String{
        return sharedPref.getString("phone","").toString()
    }

    fun setProfileGender(isSnow: String) {
        sharedPref.edit().putString("gender", isSnow).apply()
    }

    fun isProfileGender():String{
        return sharedPref.getString("gender","").toString()
    }

    fun setProfileDate(isSnow: String) {
        sharedPref.edit().putString("date", isSnow).apply()
    }

    fun isProfileDate():String{
        return sharedPref.getString("date","").toString()
    }

    fun isProfileShowed():Boolean{
        return sharedPref.getBoolean("red",false)
    }

    fun setProfileShowed(isSnow:Boolean){
        sharedPref.edit().putBoolean("red",isSnow).apply()
    }

}