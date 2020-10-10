package me.darkes.notes.repository

import me.darkes.notes.model.Note
import me.darkes.notes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : JpaRepository<Note, Long> {
    fun findByUserId(userId: Long) : Set<Note>
}
