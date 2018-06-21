package com.blackspider.objectboxdemo.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blackspider.objectboxdemo.R
import com.blackspider.objectboxdemo.data.local.note.Note
import java.text.DateFormat

/*
*  ****************************************************************************
*  * Created by : Arhan Ashik on 6/21/2018 at 3:30 PM.
*  * Email : ashik.pstu.cse@gmail.com
*  * 
*  * Last edited by : Arhan Ashik on 6/21/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class NoteAdapter(val notes:List<Note>): RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder{
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder:NoteViewHolder, position: Int) {
        val note = notes.get(position)

        holder.tvTitle.text = note.title
        holder.tvDate.text = DateFormat.getDateTimeInstance().format(note.date)
        holder.tvNote.text = note.note
    }

}

class NoteViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)!!
    val tvDate = view.findViewById<TextView>(R.id.tv_date)!!
    val tvNote = view.findViewById<TextView>(R.id.tv_note)!!
}