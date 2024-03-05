package com.example.viewmodel

import android.os.AsyncTask
import com.example.repository.UserRepositoryLogin

@Suppress("DEPRECATION")
class LoginTask(private val userRepositoryImpl: UserRepositoryLogin, private val callback: LoginCallback) :
    AsyncTask<String, Void, Boolean>(){
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): Boolean {
        val email = params[0] ?: return false
        val password = params[1] ?: return false
        return userRepositoryImpl.userLogin(email, password)
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        callback.onLoginComplete(result)
    }

    interface LoginCallback {
        fun onLoginComplete(success: Boolean)
    }
}