package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.User
import com.example.repository.RepositoryImpl
import com.example.repository.UserRepository

@Suppress("DEPRECATION")
class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    constructor() : this(RepositoryImpl())
    fun registerUser(user: User, callback: RegisterTask.RegisterCallback) {
        val registerTask = RegisterTask(userRepository, callback)
        registerTask.execute(user)
    }
}

