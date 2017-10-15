package com.xhstormr.note.service

import com.xhstormr.note.entity.Note

interface NoteService {
    fun get(id: Long): Note
    fun getAll(): List<Note>
    fun save(note: Note, content: String): Note
    fun delete(id: Long)
    fun markdown(content: String): String

    companion object {
        val separator: String = System.getProperty("line.separator")
    }
}
