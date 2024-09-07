package org.mbds.unice.github.data.repository

import org.mbds.unice.github.data.api.ApiService
import org.mbds.unice.github.data.model.User

class UserRepository(
    private val apiService: ApiService
) {
    fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    fun addRandomUser() {
        apiService.addRandomUser()
    }

    fun deleteUser(user: User) {
        apiService.deleteUser(user)
    }

    fun sortUsersByName(ascending: Boolean) {
        apiService.sortUsersByName(ascending)
    }

    fun sortUsersByDate(ascending: Boolean) {
        apiService.sortUsersByDate(ascending)
    }

    fun filterUsersByStatus(active: Boolean) {
        apiService.filterUsersByStatus(active)
    }
}