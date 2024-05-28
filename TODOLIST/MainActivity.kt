package com.example.myapplication




import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTaskTitle: EditText
    private lateinit var editTextTaskDescription: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView
    private lateinit var tasks: ArrayList<Pair<String, String>>
    private lateinit var adapter: TaskAdapter
    private val sharedPrefKey = "task_list"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextTaskTitle = findViewById(R.id.editTextTaskTitle)
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)


        tasks = ArrayList()
        adapter = TaskAdapter(this, tasks)
        listViewTasks.adapter = adapter


        loadTasksFromSharedPreferences()


        buttonAddTask.setOnClickListener {
            val title = editTextTaskTitle.text.toString().trim()
            val description = editTextTaskDescription.text.toString().trim()
            if (title.isNotEmpty()) {
                tasks.add(Pair(title, description))
                adapter.notifyDataSetChanged()
                saveTasksToSharedPreferences()
                editTextTaskTitle.text.clear()
                editTextTaskDescription.text.clear()
            }
        }


        listViewTasks.setOnItemClickListener { _, view, position, _ ->
            showPopupMenu(view, position)
        }
    }

    private fun showPopupMenu(view: android.view.View, position: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_delete -> {
                    tasks.removeAt(position)
                    adapter.notifyDataSetChanged()
                    saveTasksToSharedPreferences()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun saveTasksToSharedPreferences() {
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val tasksAsString = tasks.map { "${it.first}:${it.second}" }.toSet()
        editor.putStringSet("tasks", tasksAsString)
        editor.apply()
    }

    private fun loadTasksFromSharedPreferences() {
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        val savedTasks = sharedPref.getStringSet("tasks", null)
        savedTasks?.let {
            tasks.clear()
            tasks.addAll(it.map { it.split(":") }.map { Pair(it[0], it[1]) })
            adapter.notifyDataSetChanged()
        }
    }
}

