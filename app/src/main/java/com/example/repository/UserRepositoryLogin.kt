package com.example.repository

interface UserRepositoryLogin {
    fun userLogin(email : String, password : String) : Boolean
}