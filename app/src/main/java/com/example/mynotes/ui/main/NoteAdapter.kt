package com.example.mynotes.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.db.notes.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val context: Context?, private val listener: (Note, Int) -> Unit) :
    RecyclerView.Adapter<NoteViewHolder>() {
    private var notes = listOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_item,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = notes.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, notes[position], listener)
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}
class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context: Context, note: Note, listener: (Note, Int) -> Unit) {
        itemView.noteTV2.text = note.note

        itemView.setOnClickListener {
            listener(note, layoutPosition)
        }
    }

}