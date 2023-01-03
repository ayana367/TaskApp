@file:Suppress("DEPRECATION")

package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.TaskAdapter


@Suppress("NAME_SHADOWING")
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        setData()
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onClick ,this::onLongClick)
    }
    private fun onClick(pos: Int){
        val task = taskAdapter.getTask(pos)
        findNavController().navigate(R.id.newTaskFragment, bundleOf(EDIT to task))

    }
    private fun onLongClick(pos: Int){
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle(getString(R.string.are_you_sure_you_want_to_delete))
            setPositiveButton(R.string.yes) { _, _ ->
                App.db.dao().deleteTask(taskAdapter.getTask(pos))
                setData()
            }
            setNegativeButton(R.string.no) { a1, _ ->
                a1.dismiss()
            }
        }.show()

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort) {
            val item = arrayOf("По Дате", "По алфавиту")
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setTitle("Сортировать по:")
                setItems(item) { _, a2 ->
                    when (a2) {
                        0 -> {
                            taskAdapter.addTask(App.db.dao().getListByDate())
                        }
                        1 -> {
                            taskAdapter.addTask(App.db.dao().getListByAlphabet())
                        }
                    }
                }
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
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
    }
    private fun setData(){
        val listOfTask = App.db.dao().getAllTask()
        taskAdapter.addTask(listOfTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val EDIT = "edit"
    }
}