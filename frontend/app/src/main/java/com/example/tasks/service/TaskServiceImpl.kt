package com.example.tasks.service

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.tasks.R
import com.example.tasks.models.Task
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class TaskServiceImpl : ITaskService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var tasks: ArrayList<Task> = ArrayList()
                for (i in 0 until response.length()) {
                    val task = response.getJSONObject(i)
                    val id = task.getInt("id")
                    val title = task.getString("title")
                    val description = task.getString("description")
                    val done = task.getBoolean("done")
                    tasks.add(Task(id, title, description, done))
                }
                completionHandler(tasks)
            },
            { error ->
                completionHandler(ArrayList<Task>())
            })
        TaskSingleton.getInstance(context).addToRequestQueue(arrayRequest)
        println(arrayRequest)
    }

    override fun getAllUndone(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/undone/all"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var tasks: ArrayList<Task> = ArrayList()
                for (i in 0 until response.length()) {
                    val task = response.getJSONObject(i)
                    val id = task.getInt("id")
                    val title = task.getString("title")
                    val description = task.getString("description")
                    val done = task.getBoolean("done")
                    tasks.add(Task(id, title, description, done))
                }
                completionHandler(tasks)
            },
            { error ->
                completionHandler(ArrayList<Task>())
            })
        TaskSingleton.getInstance(context).addToRequestQueue(arrayRequest)
        println(arrayRequest)
    }

    override fun getAllDone(context: Context, completionHandler: (response: ArrayList<Task>?) -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/done/all"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var tasks: ArrayList<Task> = ArrayList()
                for (i in 0 until response.length()) {
                    val task = response.getJSONObject(i)
                    val id = task.getInt("id")
                    val title = task.getString("title")
                    val description = task.getString("description")
                    val done = task.getBoolean("done")
                    tasks.add(Task(id, title, description, done))
                }
                completionHandler(tasks)
            },
            { error ->
                completionHandler(ArrayList<Task>())
            })
        TaskSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(context: Context, taskId: Int, completionHandler: (response: Task?) -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/" + taskId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if(response == null) completionHandler(null)

                val id = response.getInt("id")
                val title = response.getString("title")
                val description = response.getString("description")
                val done = response.getBoolean("done")

                val task = Task(id,title,description,done)
                completionHandler(task)
            },
            { error ->
                completionHandler(null)
            })
        TaskSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, taskId: Int, completionHandler: () -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/" + taskId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("Hello, world", "It has been deleted")
                completionHandler()
            },
            { error ->
                Log.v("Hello, world", "There's an error")
                completionHandler()
            })
        TaskSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateTask(context: Context, task: Task, completionHandler: () -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/" + task.id
        val taskJson: JSONObject = JSONObject()
        taskJson.put("id", task.id.toString())
        taskJson.put("title", task.title)
        taskJson.put("description", task.description)
        taskJson.put("done", task.done)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, taskJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        TaskSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createTask(context: Context, task: Task, completionHandler: () -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/"
        val taskJson = JSONObject()
        taskJson.put("id", task.id.toString())
        taskJson.put("title", task.title)
        taskJson.put("description", task.description)
        taskJson.put("done", task.done)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, taskJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        TaskSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun copyTask(context: Context, taskId: Int, completionHandler: () -> Unit) {
        val path = TaskSingleton.getInstance(context).baseUrl + "/api/tasks/copy/" + taskId
        val taskJson = JSONObject()

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, taskJson, {
            response -> completionHandler() },
            {error -> completionHandler() })
        TaskSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}