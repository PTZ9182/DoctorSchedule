package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.repository.DoctorRepository
import com.example.repository.DoctorRepositoryImpl


@Suppress("DEPRECATION")
class DoctorViewModel(private val doctorRepository: DoctorRepository) : ViewModel() {

    constructor() : this(DoctorRepositoryImpl())

    fun doctorUser(nama: String, password: String, callback: DoctorTask.LoginCallback) {
        val doctorTask = DoctorTask(doctorRepository, callback)
        doctorTask.execute(nama,password)
    }
}