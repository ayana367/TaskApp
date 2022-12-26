package com.example.taskapp.ui.home.new_task

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.App
import com.example.taskapp.databinding.TaskItemBinding
import com.example.taskapp.extenssion.loadImage

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
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
                val option = arrayOf("Нет", "Удалить")
                val alert = AlertDialog.Builder(itemView.context)
                alert.setTitle("Вы точно хотите удалить?").setItems(option,
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        if (i == 0) {
                        } else if (i == 1) {
                            deleteTask(adapterPosition)
                            apply {
                                val item = taskList[position]
                                (taskList as MutableList<TaskModel>).remove(item)
                                notifyItemChanged(position)
                                App.db.dao().deleteTask(item)
                            }
                        }
                    }).show()
                return@setOnLongClickListener true
            }
        }
    }

    private fun deleteTask(i: Int) {
        taskList.removeAt(i)
        notifyDataSetChanged()
    }
}