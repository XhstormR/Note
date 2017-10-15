package com.xhstormr.note.service

import com.xhstormr.note.entity.Note
import com.xhstormr.note.repository.NoteRepository
import com.xhstormr.note.util.CMark
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
open class NoteServiceImpl(private val noteRepository: NoteRepository) : NoteService {

    override fun get(id: Long): Note = noteRepository.findOne(id)

    override fun getAll(): List<Note> = noteRepository.findAll(Sort("id"))

    @Transactional
    override fun delete(id: Long) = noteRepository.delete(id)

    @Transactional
    override fun save(note: Note, content: String): Note {
        note.editNum++
        note.charNum = content.count().toLong()
        note.title = content.split(NoteService.separator)[0]
        note.modifyTime = Date()
        note.details.content = content
        return noteRepository.save(note)
    }

    override fun markdown(content: String) = content.split(NoteService.separator).let { CMark.execute(it) }.joinToString(separator = "")
}
