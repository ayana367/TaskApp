package com.example.taskapp.extenssion

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskapp.R

fun View.loadImage(url:String){
  Glide.with(this).load(url).placeholder(R.drawable.profiles).centerCrop().into(this as ImageView)
}
fun Fragment.snowToast(msg:String){
  Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}