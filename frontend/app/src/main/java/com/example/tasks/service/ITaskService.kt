package com.example.tasks.service

import android.content.Context
import com.example.tasks.models.Task

interface ITaskService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit)

    fun getById(context: Context, taskId: Int, completionHandler: (response: Task?) -> Unit)

    fun deleteById(context: Context, taskId: Int, completionHandler: () -> Unit)

    fun updateTask(context: Context, task: Task, completionHandler: () -> Unit)

    fun createTask(context: Context, task: Task, completionHandler: () -> Unit)

    fun getAllUndone(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit)

    fun getAllDone(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit)

    fun copyTask(context: Context, taskId: Int, completionHandler: () -> Unit)

}