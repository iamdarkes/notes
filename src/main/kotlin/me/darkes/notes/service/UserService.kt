package me.darkes.notes.service

import me.darkes.notes.model.Note
import me.darkes.notes.model.User
import me.darkes.notes.repository.NoteRepository
import me.darkes.notes.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val noteRepository: NoteRepository) {

    fun createUser(request: User): DTOUser {
        //to prevent exception if I pass id for user of "1" and user is first user
//        request.id?.let { id ->
//            val exists = userRepository.findById(id)
//            if (!exists.isPresent) {
//                request.id = null
//            }
//        }

        request.notes.forEach { it.user = request }
        val user = userRepository.save(request)


        user.notes = noteRepository.findByUserId(user.id!!)
        return DTOUser(user.id, user.name, user.notes.map { DTONote(it.id, it.description, it.text) }.toSet())
    }

    fun findById(userId: Long): DTOUser {
        val user = userRepository.findById(userId).get()
        user.notes = noteRepository.findByUserId(user.id!!)

        return DTOUser(user.id, user.name, user.notes.map { DTONote(it.id, it.description, it.text) }.toSet())
    }


}

data class DTOUser(val id: Long?, val name: String?, val notes: Set<DTONote>)
data class DTONote(val id: Long?, val description: String?, val text: String?)
data class UserDTO(val user: User, val notes: Set<Note>)
