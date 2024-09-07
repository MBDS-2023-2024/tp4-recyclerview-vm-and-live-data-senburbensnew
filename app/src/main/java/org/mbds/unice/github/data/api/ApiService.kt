package org.mbds.unice.github.data.api

import org.mbds.unice.github.data.model.User

interface ApiService {
    fun getUsers(): List<User>
    fun addRandomUser()
    fun deleteUser(username: User)
    fun sortUsersByName(ascending: Boolean)
    fun sortUsersByDate(ascending: Boolean)
    fun filterUsersByStatus(active: Boolean)
    fun searchUserByUsername(newText: String?): List<User>
}