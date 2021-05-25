package com.example.mynotes.db.notes

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotes.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteRepository(application: Application) {

    private val noteDAO: NoteDAO?
    private var notes: LiveData<List<Note>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        noteDAO = db?.noteDao()
        notes = noteDAO?.getNotes()
    }

    fun getNotes(): LiveData<List<Note>>? {
        return notes
    }

    fun insert(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDAO?.insertNote(note)
        }
    }

    fun delete(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDAO?.deleteNote(note)
            }
        }
    }

    fun update(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDAO?.updateNote(note)
        }
    }

}