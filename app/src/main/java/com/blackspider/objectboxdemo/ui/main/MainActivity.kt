package com.blackspider.objectboxdemo.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.blackspider.AppController
import com.blackspider.objectboxdemo.data.local.note.Note
import com.blackspider.objectboxdemo.R
import com.blackspider.objectboxdemo.data.local.note.Note_
import io.objectbox.Box
import io.objectbox.BoxStore

class MainActivity : AppCompatActivity() {
    private lateinit var store: BoxStore
    private lateinit var noteBox: Box<Note>

    private lateinit var rvNotes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        store = (application as AppController).boxStore
        noteBox = store.boxFor(Note::class.java)

        rvNotes = findViewById(R.id.rv_notes)
        initRecyclerView()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val note = Note(
                    title = "First title",
                    note = "First note",
                    date = System.currentTimeMillis()
            )
            val inserted = insertUpdateNote(note)

            Toast.makeText(this, "inserted: $inserted", Toast.LENGTH_SHORT).show()
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
        rvNotes.adapter = NoteAdapter(getNotes())
    }

    private fun insertUpdateNote(note: Note): Long {
        return noteBox.put(note)
    }

    private fun getNote(id: Long): Note {
        return noteBox.get(id)
    }

    private fun getNotes(): List<Note>{
        return noteBox.all
    }

    private fun findNotes(searchKey: String): List<Note>{
        val builder = noteBox.query()
        builder.contains(Note_.note, searchKey)

        return builder.build().find()
    }

    private fun delete(id: Long){

        return noteBox.remove(id)
    }

    private fun delete(note: Note){

        return noteBox.remove(note)
    }
}
