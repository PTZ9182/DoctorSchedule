package com.example.repository

interface DoctorRepository {

    fun doctorLogin(nama: String, password: String) : Boolean
}