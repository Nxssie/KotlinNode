package com.example.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.models.Task
import com.example.tasks.service.TaskServiceImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        recyclerView = findViewById<RecyclerView>(R.id.recyclcerViewTasks)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllTasks()

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        fab.setOnClickListener{
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("state", "Adding")
            startActivity(intent)
        }


    }

    private fun getAllTasksLocally() {
        //bicycles = ArrayList<Bicycle>();

        tasks.add(Task(1, "Sacar al perro", "Sacar al perro 3 veces por semana", false))
        tasks.add(Task(2, "Lavar los platos", "Lavar los platos despues del almuerzo",true))
    }

    private fun getAllTasks() {
        val bicycleServiceImpl = TaskServiceImpl()
        bicycleServiceImpl.getAll(this) { response ->
            run {
                if (response != null) {
                    viewAdapter.taskList = response
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }
    

}