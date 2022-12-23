package com.example.taskapp.ui.home.new_task

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    var imgUri : String = ""

    private var mGetContent = this.registerForActivityResult<String, Uri>(
        ActivityResultContracts.GetContent()
    ){ uri ->
        binding.imageNewTask.setImageURI(uri)
        imgUri = uri.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context),container,false)

        initListeners()
        return binding.root
    }
    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            setFragmentResult(
                "new_task",
                bundleOf(
                    "data" to TaskModel(imgUri,binding.etTitle.text.toString(),binding.etDesc.text.toString(),binding.etData.text.toString())))

            findNavController().navigateUp()
        }
        binding.imageNewTask.setOnClickListener{
            mGetContent.launch("image/*")
        }
    }
}

