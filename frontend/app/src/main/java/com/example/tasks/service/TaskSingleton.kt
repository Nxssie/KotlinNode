package com.example.tasks.service

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class TaskSingleton constructor(context: Context) {
    val baseUrl = "http://192.168.1.10:8080"

    companion object {
        @Volatile
        private var INSTANCE: TaskSingleton? = null

        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: TaskSingleton(context).also {
                        INSTANCE = it
                    }
                }
    }

    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}