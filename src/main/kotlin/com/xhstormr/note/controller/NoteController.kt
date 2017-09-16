package com.xhstormr.note.controller

import com.xhstormr.note.entity.Note
import com.xhstormr.note.service.NoteService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/notes")
class NoteController(private val noteService: NoteService) {
    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("notes", noteService.getAll())
        return "list"
    }

    @GetMapping("/new")
    fun new_get(model: Model): String {
        model.addAttribute("note", Note(title = "新建记事"))
        return "edit"
    }

    @GetMapping("/edit/{id}")
    fun edit_get(@PathVariable id: Long, model: Model): String {
        model.addAttribute("note", noteService.get(id))
        return "edit"
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val note = noteService.get(id)
        val data = noteService.markDown(note.details.content)
        model.addAttribute("note", note)
        model.addAttribute("data", data)
        return "note"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        noteService.delete(id)
        return "redirect:/notes"
    }

    @PostMapping("/new")
    fun new_post(content: String): String {
        val note = noteService.save(Note(), content)
        return "redirect:/notes/${note.id}"
    }

    @PostMapping("/edit/{id}")
    fun edit_post(content: String, @PathVariable id: Long): String {
        val note = noteService.get(id)
        noteService.save(note, content)
        return "redirect:/notes/${note.id}"
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleError(e: Exception): String {
        e.printStackTrace()
        return "NOT FOUND"
    }
}
