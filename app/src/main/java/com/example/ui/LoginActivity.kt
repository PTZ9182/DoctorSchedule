package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.MainActivity
import com.example.doctorschedule.R
import com.example.doctorschedule.databinding.ActivityLoginBinding
import com.example.repository.UserRepositoryImpl
import com.example.repository.UserRepositoryLogin
import com.example.viewmodel.LoginTask
import com.example.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepositoryLogin: UserRepositoryLogin
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepositoryLogin = UserRepositoryImpl()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupUI()

    }

    private fun setupUI() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.isiformEmail.text.toString()
            val password = binding.isiformPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password, object : LoginTask.LoginCallback {
                    override fun onLoginComplete(success: Boolean) {
                        if (success) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login gagal. Periksa email dan password Anda.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this@LoginActivity, "Email dan password harus diisi.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}