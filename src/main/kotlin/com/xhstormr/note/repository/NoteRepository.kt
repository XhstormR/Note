package com.xhstormr.note.repository

import com.xhstormr.note.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long> {
    fun findByTitleNot(title: String): List<Note>

    fun findByTitleIgnoreCase(title: String): Note
}
