package com.example.taskapp.ui.home.new_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
@PrimaryKey(autoGenerate = true)
    val id : Long? = null,
    val image : String,
val title : String,
val description : String,
val data : String
):java.io.Serializable
