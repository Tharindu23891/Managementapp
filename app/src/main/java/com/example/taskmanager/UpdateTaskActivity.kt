package com.example.taskmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.databinding.ActivityUpdateTaskBinding


class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TasksDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskById(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateTask = Task(taskId, newTitle, newContent)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changes Saved", Toast.LENGTH_SHORT).show()

        }

    }
}