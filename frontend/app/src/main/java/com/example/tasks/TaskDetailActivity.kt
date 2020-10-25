package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.models.Task
import com.example.tasks.service.TaskServiceImpl
import com.google.android.material.textfield.TextInputEditText

class TaskDetailActivity : AppCompatActivity(){
    private lateinit var state: String
    private lateinit var textInputEditTextTitle: EditText
    private lateinit var textInputEditTextDesc: EditText
    private lateinit var checkBoxDone: CheckBox
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonDuplicate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        state = this.intent.getStringExtra("state").toString()
        Log.v("Current state:", state)

        val taskid = this.intent.getIntExtra("taskId", 1)
        Log.v("Task ID:", taskid.toString())

        textInputEditTextTitle = findViewById(R.id.TextInputEditTextTitle)
        textInputEditTextDesc = findViewById(R.id.TextInputEditTextDesc)
        checkBoxDone = findViewById(R.id.checkBoxDone)

        textInputEditTextTitle.isEnabled = false
        textInputEditTextDesc.isEnabled = false
        checkBoxDone.isEnabled = false

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteTask(taskid)
        }

        buttonDuplicate = findViewById(R.id.buttonDuplicate)
        buttonDuplicate.setOnClickListener{
            copyTask(taskid)
        }

        if(state == "Showing") getTask(taskid)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    Log.v("Showing:", state)
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    Log.v("Editing:", state)
                    val task = Task(taskid, textInputEditTextTitle.text.toString(), textInputEditTextDesc.text.toString(), checkBoxDone.isChecked)
                    Log.v("Task ID:", task.id.toString())
                    Log.v("Task title:", task.title)
                    Log.v("Task description:", task.description)
                    Log.v("Task done:", task.done.toString())
                    updateTask(task)
                }
                "Adding" -> {
                    Log.v("Adding:", state)
                    val task = Task(taskid, textInputEditTextTitle.text.toString(), textInputEditTextDesc.text.toString(), checkBoxDone.isChecked)
                    createTask(task)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateTask(task: Task) {
        val taskServiceImpl = TaskServiceImpl()
        taskServiceImpl.updateTask(this, task) { ->
            run {
                Log.v("Updating:", task.title)
                changeButtonsToShowing(task.id)
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createTask(task: Task) {
        val taskServiceImpl = TaskServiceImpl()
        taskServiceImpl.createTask(this, task) { ->
            run {
                changeButtonsToShowing(task.id)
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun copyTask(taskid: Int) {
        val taskServiceImpl = TaskServiceImpl()
        taskServiceImpl.copyTask(this, taskid) { ->
            run {
                changeButtonsToShowing(taskid)
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDuplicate.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.isEnabled = true
        buttonEdit.setText("Add Task")
        textInputEditTextTitle.isEnabled = true
        textInputEditTextDesc.isEnabled = true
        checkBoxDone.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(taskId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.isEnabled=true
        buttonEdit.setText("Edit Task")
        textInputEditTextTitle.isEnabled = false
        textInputEditTextDesc.isEnabled = false
        checkBoxDone.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = true
        buttonDuplicate.visibility = View.GONE
        buttonEdit.setText("Apply changes")
        textInputEditTextTitle.isEnabled = true
        textInputEditTextDesc.isEnabled = true
        checkBoxDone.isEnabled = true
        state = "Editing"
    }

    private fun getTask(taskid: Int) {
        val taskServiceImpl = TaskServiceImpl()
        taskServiceImpl.getById(this, taskid) { response ->
            run {
                Log.v("Task ID:", taskid.toString())
                val txt_title: TextInputEditText = findViewById(R.id.TextInputEditTextTitle)
                val txt_description: TextInputEditText = findViewById(R.id.TextInputEditTextDesc)
                val checkbox_done: CheckBox = findViewById(R.id.checkBoxDone)

                txt_title.setText(response?.title ?: "")
                txt_description.setText(response?.description ?: "")
                checkbox_done.isChecked = response?.done ?: false
            }
        }
    }

    private fun deleteTask(taskid: Int) {
        val taskServiceImpl = TaskServiceImpl()
        taskServiceImpl.deleteById(this, taskid) { ->
            run {
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}