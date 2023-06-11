package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(mutableListOf())

        val rvTodoItems = findViewById<RecyclerView>(R.id.rvToDoItems)
        rvTodoItems.adapter = taskAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val etTaskTitle = findViewById<EditText>(R.id.etToDoTitle)
        val addButton = findViewById<Button>(R.id.btnAddTask)
        addButton.setOnClickListener {
            val taskTitle = etTaskTitle.text.toString()
            if (taskTitle.isNotEmpty()){
                val task = Task(taskTitle)
                taskAdapter.addTask(task)
                etTaskTitle.text.clear()
            }
        }
        val deleteDoneButton = findViewById<Button>(R.id.btnDeleteTask)
        deleteDoneButton.setOnClickListener {
            taskAdapter.deleteDoneTasks()
        }
    }
}