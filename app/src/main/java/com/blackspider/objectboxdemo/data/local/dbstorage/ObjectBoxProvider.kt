package com.blackspider.objectboxdemo.data.local.dbstorage

import com.blackspider.AppController
import com.blackspider.objectboxdemo.data.local.note.Note
import com.blackspider.objectboxdemo.data.local.note.Note_
import io.objectbox.Box
import io.objectbox.BoxStore

/*
*  ****************************************************************************
*  * Created by : Arhan Ashik on 6/22/2018 at 11:32 AM.
*  * Email : ashik.pstu.cse@gmail.com
*  * 
*  * Last edited by : Arhan Ashik on 6/22/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class ObjectBoxProvider (application: AppController){
    private var store: BoxStore = application.boxStore
    private var noteBox: Box<Note>

    init {
        noteBox = store.boxFor(Note::class.java)
    }

    fun insertUpdateNote(note: Note): Long {
        return noteBox.put(note)
    }

    fun getNote(id: Long): Note {
        return noteBox.get(id)
    }

    fun getNotes(): List<Note>{
        return noteBox.all
    }

    fun findNotes(searchKey: String): List<Note>{
        val builder = noteBox.query()
        builder.contains(Note_.note, searchKey)

        return builder.build().find()
    }

    fun delete(id: Long){

        return noteBox.remove(id)
    }

    fun delete(note: Note){

        return noteBox.remove(note)
    }
}