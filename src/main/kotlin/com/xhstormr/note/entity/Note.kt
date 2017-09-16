package com.xhstormr.note.entity

import java.util.*
import javax.persistence.*

@Entity
data class Note(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        @Temporal(TemporalType.TIMESTAMP)
        var modifyTime: Date = Date(),
        var charNum: Long = 0,
        var editNum: Long = 0,
        var title: String = "",
        @OneToOne(mappedBy = "note", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        var details: NoteDetail = NoteDetail()
) {
    init {
        this.addDetails(details)
    }

    private fun addDetails(details: NoteDetail) {
        this.details = details
        details.note = this
    }
}
