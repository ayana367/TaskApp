package com.example.taskapp.ui.home.new_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long? = null,
    var image : String,
    var title : String,
    var description : String,
    var data : String
):java.io.Serializable
