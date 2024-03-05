package com.example.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.doctorschedule.databinding.ActivityRegisterBinding
import com.example.model.User
import com.example.repository.RepositoryImpl
import com.example.viewmodel.RegisterTask
import com.example.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity(), RegisterTask.RegisterCallback {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = RepositoryImpl()

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        setupUI()
    }

    private fun setupUI() {
        binding.rgButtonDaftar.setOnClickListener {
            val nama = binding.rgIsiformNama.text.toString()
            val email = binding.rgIsiformEmail.text.toString()
            val password = binding.rgIsiformPassword.text.toString()

            if (nama.isBlank() || email.isBlank() || password.isBlank()) {
                val errorMessage = "Kolom Harus Diisi"

                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Format email tidak valid.", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(
                    this,
                    "Password harus terdiri dari minimal 8 karakter.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if(nama.isBlank()) {
                Toast.makeText(this,"Nama harus diisi.",Toast.LENGTH_SHORT).show()

            } else if(email.isBlank()) {
                Toast.makeText(this,"Email harus diisi.",Toast.LENGTH_SHORT).show()

            } else if(password.isBlank()) {
                Toast.makeText(this,"Password harus diisi.",Toast.LENGTH_SHORT).show()
            } else {
                val user = User(nama, email, password)
                viewModel.registerUser(user, this)
            }
        }
    }

    override fun onRegisterComplete(success: Boolean) {
        if (success) {
            showRegistrationSuccessMessage()
            navigateToLoginActivity()
        } else {
            showRegistrationFailureMessage()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun showRegistrationSuccessMessage() {
        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
    }

    private fun showRegistrationFailureMessage() {
        Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
