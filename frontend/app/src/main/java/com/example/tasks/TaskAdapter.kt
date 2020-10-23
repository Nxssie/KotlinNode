package com.example.tasks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.models.Task

class TaskAdapter(var taskList: ArrayList<Task>, val context: Context) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v :View = LayoutInflater.from(parent.context).inflate(R.layout.task_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(taskList[position], context)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(t: Task, context: Context){
            val rowLayout : LinearLayout = itemView.findViewById(R.id.rowLayout)
            val txt_title: TextView = itemView.findViewById(R.id.textViewTitle)
            val txt_desc: TextView = itemView.findViewById(R.id.textViewDesc)
            val box_done: CheckBox = itemView.findViewById(R.id.checkBoxDone)

            rowLayout.setOnClickListener{
                val intent: Intent = Intent(context, TaskDetailActivity::class.java)
                intent.putExtra("taskId", t.id)
                context.startActivity(intent)
            }

            txt_title.text = t.title
            txt_desc.text = t.description
            box_done.isChecked = t.done
        }
    }

}