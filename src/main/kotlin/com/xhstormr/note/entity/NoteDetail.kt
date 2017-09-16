package com.xhstormr.note.entity

import javax.persistence.*

@Entity
data class NoteDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        @Column(nullable = false, columnDefinition = "text")
        var content: String = "",
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(unique = true)
        var note: Note? = null
)
