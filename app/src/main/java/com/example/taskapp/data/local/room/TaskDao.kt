package com.example.taskapp.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskapp.ui.home.new_task.TaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskModel)

    @Query("SELECT* FROM TaskModel ")
    fun getAllTask():List<TaskModel>

    @Delete
    fun deleteTask(task: TaskModel)

    @Query("SELECT * FROM TaskModel ORDER BY id DESC")
    fun getListByDate():List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title ASC")
    fun getListByAlphabet():List<TaskModel>

    @Update
    fun updateTask(taskModel: TaskModel)
}