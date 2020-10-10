package me.darkes.notes.service

import me.darkes.notes.model.User
import me.darkes.notes.repository.NoteRepository
import me.darkes.notes.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val noteRepository: NoteRepository) {

    fun createUser(request: User): UserDTO {
        request.notes.forEach { it.user = request }
        val user = userRepository.save(request)


        user.notes = noteRepository.findByUserId(user.id!!)
        return UserDTO(user.id, user.name, user.notes.map { NoteDTO(it.id, it.description, it.text) }.toSet())
    }

    fun findById(userId: Long): UserDTO {
        val user = userRepository.findById(userId).get()
        user.notes = noteRepository.findByUserId(user.id!!)

        return UserDTO(user.id, user.name, user.notes.map { NoteDTO(it.id, it.description, it.text) }.toSet())
    }


}

data class UserDTO(val id: Long?, val name: String?, val notes: Set<NoteDTO>)
data class NoteDTO(val id: Long?, val description: String?, val text: String?)
