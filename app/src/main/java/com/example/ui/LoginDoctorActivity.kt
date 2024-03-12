package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.MainActivity
import com.example.doctorschedule.databinding.ActivityLoginDoctorBinding
import com.example.repository.DoctorRepository
import com.example.repository.DoctorRepositoryImpl
import com.example.viewmodel.DoctorTask
import com.example.viewmodel.DoctorViewModel

class LoginDoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginDoctorBinding
    private lateinit var doctorRepository: DoctorRepository
    private lateinit var viewModel: DoctorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doctorRepository = DoctorRepositoryImpl()
        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)

        setupUI()

    }

    private fun setupUI() {
        binding.buttonLoginDoctor.setOnClickListener {
            val nama = binding.isiformNamaDoctor.text.toString()
            val password = binding.isiformPasswordDoctor.text.toString()

            if (nama.isNotEmpty() && password.isNotEmpty()) {
                viewModel.doctorUser(nama, password, object : DoctorTask.LoginCallback {
                    override fun onLoginComplete(success: Boolean) {
                        if (success) {
                            startActivity(Intent(this@LoginDoctorActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginDoctorActivity, "Login gagal. Periksa email dan password Anda.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this@LoginDoctorActivity, "Email dan password harus diisi.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}