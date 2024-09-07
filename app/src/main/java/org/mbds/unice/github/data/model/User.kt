package org.mbds.unice.github.data.model

import java.util.Date

data class User(
    val id: String,
    val login: String,
    val avatarUrl: String,
    var creationDate : Date,
    var active : Boolean
)