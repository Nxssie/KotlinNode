package com.example.tasks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        state = this.intent.getStringExtra("state").toString()

        val taskid = this.intent.getIntExtra("taskId", 1)

        textInputEditTextTitle = findViewById(R.id.TextInputEditTextTitle)
        textInputEditTextDesc = findViewById(R.id.TextInputEditTextDesc)
        checkBoxDone = findViewById(R.id.checkBoxDone)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteTask(taskid)
        }

        if(state == "Showing") getTask(taskid)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val task = Task(taskId, textInputEditTextTitle.text.toString(), textInputEditTextDesc.text.toString(), checkBoxDone.isChecked)
                    updateBicycle(task)
                }
                "Adding" -> {
                    val task = Task(taskId, textInputEditTextTitle.text.toString(), textInputEditTextDesc.text.toString(), checkBoxDone.isChecked)
                    createBicycle(task)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateBicycle(task: Task) {
        val bicycleServiceImpl = TaskServiceImpl()
        bicycleServiceImpl.updateTask(this, task) { ->
            run {
                changeButtonsToShowing(task.id)
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createBicycle(task: Task) {
        val bicycleServiceImpl = TaskServiceImpl()
        bicycleServiceImpl.createTask(this, task) { ->
            run {
                changeButtonsToShowing(task.id)
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add Task")
        textInputEditTextTitle.isEnabled = true
        textInputEditTextDesc.isEnabled = true
        checkBoxDone.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(taskId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Task")
        textInputEditTextTitle.isEnabled = false
        textInputEditTextDesc.isEnabled = false
        checkBoxDone.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextTitle.isEnabled = true
        textInputEditTextDesc.isEnabled = true
        checkBoxDone.isEnabled = true
        state = "Editing"
    }

    private fun getTask(taskId: Int) {
        val bicycleServiceImpl = TaskServiceImpl()
        bicycleServiceImpl.getById(this, taskId) { response ->
            run {

                val txt_title: TextInputEditText = findViewById(R.id.TextInputEditTextTitle)
                val txt_description: TextInputEditText = findViewById(R.id.TextInputEditTextDesc)
                val checkbox_done: CheckBox = findViewById(R.id.checkBoxDone)

                txt_title.setText(response?.title ?: "")
                txt_description.setText(response?.description ?: "")
            }
        }
    }

    private fun deleteTask(taskId: Int) {
        val bicycleServiceImpl = TaskServiceImpl()
        bicycleServiceImpl.deleteById(this, taskId) { ->
            run {
                val intent = Intent(this, TaskListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}