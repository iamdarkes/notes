package me.darkes.notes.controller

import me.darkes.notes.model.User
import me.darkes.notes.service.DTOUser
import me.darkes.notes.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/user/{userId}")
    fun get(@PathVariable userId: Long): DTOUser {
        return userService.findById(userId)
    }

    @PostMapping("/user")
    fun create(@RequestBody user: User) : DTOUser {
        return userService.createUser(user)
    }
}
