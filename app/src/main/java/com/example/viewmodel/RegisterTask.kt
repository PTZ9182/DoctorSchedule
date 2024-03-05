@file:Suppress("DEPRECATION")

package com.example.viewmodel

import android.os.AsyncTask
import com.example.model.User
import com.example.repository.UserRepository

@Suppress("DEPRECATION")
class RegisterTask(private val userRepository: UserRepository, private val callback: RegisterCallback) :
    AsyncTask<User, Void, Boolean>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: User?): Boolean {
        val user = params[0] ?: return false
        return userRepository.userRegister(user.nama, user.email, user.password)
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        callback.onRegisterComplete(result)
    }

    interface RegisterCallback {
        fun onRegisterComplete(success: Boolean)
    }
}
