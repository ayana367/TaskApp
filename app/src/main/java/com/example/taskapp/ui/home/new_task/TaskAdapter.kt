package com.example.taskapp.ui.home.new_task

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.App
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.extenssion.loadImage
import java.nio.file.attribute.AclEntry.Builder
import kotlin.random.Random

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var taskList = arrayListOf<TaskModel>()

    fun addTask(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    var mColor = arrayOf("#FFFFFFFF","#FF464242")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
        holder.itemView.setBackgroundColor(Color.parseColor(mColor[position % 2]))
    }


    override fun getItemCount(): Int = taskList.size

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
            binding.image.loadImage(taskModel.image)
            binding.data.text = taskModel.data
            itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(itemView.context)
                with(builder) {
                    setTitle("Вы точно хотите удалить? ${taskModel.title}")
                    setPositiveButton("Да") { a1, a2 ->
                        App.db.dao().deleteTask(taskModel)
                        taskList.clear()
                        taskList.addAll(App.db.dao().getAllTask())
                        notifyDataSetChanged()
                    }
                    setNegativeButton("Нет") { a1, a2 ->
                        a1.dismiss()
                    }
                }.show()
                return@setOnLongClickListener true
            }
        }
    }

}

