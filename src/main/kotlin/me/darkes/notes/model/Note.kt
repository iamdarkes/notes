package me.darkes.notes.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var description: String? = null

    @Column
    var text: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    //don't need to put this
//    @JoinColumn(name = "user_id")
    var user: User? = null
}
