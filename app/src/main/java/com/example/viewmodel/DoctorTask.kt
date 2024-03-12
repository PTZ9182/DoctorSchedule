package com.example.viewmodel

import android.os.AsyncTask
import com.example.repository.DoctorRepository

@Suppress("DEPRECATION")
class DoctorTask(private val doctorRepositoryImpl: DoctorRepository, private val callback: LoginCallback):
    AsyncTask<String, Void, Boolean>() {
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): Boolean {
        val nama = params[0] ?: return false
        val password = params[1] ?: return false
        return doctorRepositoryImpl.doctorLogin(nama, password)
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        callback.onLoginComplete(result)
    }

    interface LoginCallback {
        fun onLoginComplete(success: Boolean)
    }
}