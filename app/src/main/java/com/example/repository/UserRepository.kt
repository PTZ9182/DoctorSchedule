package com.example.repository

interface UserRepository {
    fun userRegister(user: String, email: String, password: String): Boolean
}