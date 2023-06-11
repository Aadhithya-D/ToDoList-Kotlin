package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return  TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTask(task: Task){
        tasks.add(task)
        notifyItemInserted(tasks.size-1)
    }

    fun deleteDoneTasks(){
        tasks.removeAll { task -> task.isChecked}
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(title:TextView, checked: Boolean){
        if (checked){
            title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var currentTask = tasks[position]
        val title = holder.itemView.findViewById<TextView>(R.id.tvTaskTitle)
        title.text = currentTask.title
        val checked = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        checked.isChecked = currentTask.isChecked
        toggleStrikeThrough(title, currentTask.isChecked)
        checked.setOnCheckedChangeListener { _, isChecked ->  toggleStrikeThrough(title, isChecked)
            currentTask.isChecked = !currentTask.isChecked
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}