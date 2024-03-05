package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.repository.UserRepositoryImpl
import com.example.repository.UserRepositoryLogin

class LoginViewModel(private val userRepositoryLogin: UserRepositoryLogin) : ViewModel() {

    constructor() : this(UserRepositoryImpl())

    fun loginUser(email: String, password: String, callback: LoginTask.LoginCallback) {
        val loginTask = LoginTask(userRepositoryLogin, callback)
        loginTask.execute(email,password)
    }
}