package com.blackspider.objectboxdemo.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.blackspider.AppController
import com.blackspider.objectboxdemo.data.local.note.Note
import com.blackspider.objectboxdemo.R
import com.blackspider.objectboxdemo.data.local.dbstorage.ObjectBoxProvider
import com.blackspider.objectboxdemo.data.local.note.Note_
import io.objectbox.Box
import io.objectbox.BoxStore

class MainActivity : AppCompatActivity() {
    private lateinit var boxProvider: ObjectBoxProvider

    private lateinit var rvNotes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        boxProvider = ObjectBoxProvider((application as AppController))

        rvNotes = findViewById(R.id.rv_notes)
        initRecyclerView()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            createNote()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun initRecyclerView() {
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = NoteAdapter(boxProvider.getNotes())
    }

    private fun createNote(){
        val dialogView = layoutInflater.inflate(R.layout.prompt_ask_question, null)
        val etTitle = dialogView.findViewById<EditText>(R.id.et_title)
        val etNote = dialogView.findViewById<EditText>(R.id.et_note)
        val btnCreate = dialogView.findViewById<Button>(R.id.btn_create)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()

        btnCreate.setOnClickListener({
            var title = etTitle.text.toString()
            val note = etNote.text.toString()

            if(TextUtils.isEmpty(note)) Toast.makeText(this, "Please write something", Toast.LENGTH_SHORT).show()
            else {
                dialog.dismiss()

                if(TextUtils.isEmpty(title)) title = "Untitled"

                val noteInstance = Note(
                        title = title,
                        note = note,
                        date = System.currentTimeMillis()
                )

                val saved = if(boxProvider.insertUpdateNote(noteInstance) > 0) "Saved successfully" else "Failed to save"

                Toast.makeText(this, saved, Toast.LENGTH_SHORT).show()

                initRecyclerView()
            }
        })

        dialog.show()
    }
}
