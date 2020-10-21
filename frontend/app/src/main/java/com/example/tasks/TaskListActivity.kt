package com.example.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.models.Task

class TaskListActivity : AppCompatActivity() {

    private lateinit var tasks: ArrayList<Task>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TaskAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        tasks = ArrayList<Task>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = TaskAdapter(tasks, this)



        getAllTasksLocally()

        (recyclerView.adapter as TaskAdapter).notifyDataSetChanged()
    }

    private fun getAllTasksLocally() {
        //bicycles = ArrayList<Bicycle>();

        tasks.add(Task("Pasear al perro", "Sacar al perro 3 veces por semana", false))
        tasks.add(Task("Lavar los platos", "Lavar los platos despues del almuerzo", true))
    }

    

}