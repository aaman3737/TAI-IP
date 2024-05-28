package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextNoteTitle: EditText
    private lateinit var editTextNoteDescription: EditText
    private lateinit var buttonSaveNote: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextNoteTitle = findViewById(R.id.editTextNoteTitle)
        editTextNoteDescription = findViewById(R.id.editTextNoteDescription)
        buttonSaveNote = findViewById(R.id.buttonSaveNote)

        buttonSaveNote.setOnClickListener {
            val title = editTextNoteTitle.text.toString().trim()
            val description = editTextNoteDescription.text.toString().trim()
            if (title.isNotEmpty()) {
                saveNoteToSharedPreferences(title, description)
                finish()
            }
        }
    }

    private fun saveNoteToSharedPreferences(title: String, description: String) {
        val sharedPref = getSharedPreferences("notes", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val currentNotes = sharedPref.getStringSet("notes", HashSet())?.toMutableSet()
        currentNotes?.add("$title:$description")
        editor.putStringSet("notes", currentNotes)
        editor.apply()
    }
}
