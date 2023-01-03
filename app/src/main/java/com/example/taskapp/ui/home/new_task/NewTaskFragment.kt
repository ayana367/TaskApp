package com.example.taskapp.ui.home.new_task

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.home.HomeFragment.Companion.EDIT

class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    private var imgUri: String = ""
    private lateinit var task:TaskModel

    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ) { uri ->
        binding.imageNewTask.setImageURI(uri)
        imgUri = uri.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if(arguments !=null) {
                updateData(task)
            }else{
                saveData()
            }

            findNavController().navigateUp()
        }
        binding.imageNewTask.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }
    private fun saveData(){
        App.db.dao().insert(TaskModel(
            image = imgUri,
            title = binding.etTitle.text.toString(),
            description = binding.etDesc.text.toString(),
            data = binding.etData.text.toString()
        ))
    }
    private fun updateData(taskModel: TaskModel){
        taskModel.title = binding.etTitle.text.toString()
        taskModel.description = binding.etDesc.text.toString()
        taskModel.data = binding.etData.text.toString()
        taskModel.image = imgUri
        App.db.dao().updateTask(taskModel)
    }

    private fun initViews(){
        if (arguments != null){
            binding.btnSave.text = getString(R.string.update)
            task = arguments?.getSerializable(EDIT) as TaskModel
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.description)
            binding.etData.setText(task.data)
            task.image = imgUri
            }else{
                binding.btnSave.text = getString(R.string.save)
        }
    }
}