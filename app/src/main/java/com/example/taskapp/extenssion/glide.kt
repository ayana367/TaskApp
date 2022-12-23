package com.example.taskapp.extenssion

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.taskapp.R

fun View.loadImage(url:String){
  Glide.with(this).load(url).placeholder(R.drawable.roz).centerCrop().into(this as ImageView)
}