package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.TaskAdapter
import com.example.taskapp.ui.home.new_task.TaskModel
import java.util.*
import kotlin.Comparator

class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter
    private var task = arrayListOf<TaskModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.atoz) {
                    sortAlphabet()
                    App.db.dao().sort()
                }
            Toast.makeText(activity,"Cортировка по алфавиту", Toast.LENGTH_LONG).show()
        if (id == R.id.data) {
                    everybodySort()
                    App.db.dao().everybodySort()
            Toast.makeText(activity, "Cортировка всего", Toast.LENGTH_LONG).show()
        }
        if (id == R.id.yb){
            data()
            App.db.dao().data()
        }

        return super.onOptionsItemSelected(item)

    }

    private fun data() {
        Collections.sort(task, Comparator<TaskModel>{ a,d ->
            a.data.compareTo(a.data)
        })
        taskAdapter.notifyDataSetChanged()
    }

    private fun everybodySort() {
        Collections.sort(task,Comparator<TaskModel>{ d, a ->
            d.title.compareTo(a.title)
            d.description.compareTo(a.description)
            d.data.compareTo(a.data)
        })
        taskAdapter.notifyDataSetChanged()
    }

    private fun sortAlphabet() {
        Collections.sort(task,Comparator <TaskModel>{ t, t2 ->
            t.title.compareTo(t2.title)
        })
        taskAdapter.notifyDataSetChanged()
    }
    private fun initListeners() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }


    private fun initViews() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

        val listOfTask = App.db.dao().getAllTask()
        taskAdapter.addTask(listOfTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}