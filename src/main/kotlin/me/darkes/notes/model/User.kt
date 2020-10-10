package me.darkes.notes.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var name: String? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var notes: Set<Note> = emptySet()
}
