package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.doctorschedule.databinding.ActivityPraloginBinding

class PraloginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPraloginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPraloginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUser()
        setDoctor()
    }

    private fun setDoctor() {
        binding.dpButtonPerusahaan.setOnClickListener{
            startActivity(Intent(this@PraloginActivity, LoginDoctorActivity::class.java))
            finish()
        }
    }

    private fun setUser() {
        binding.dpButtonKaryawan.setOnClickListener{
            startActivity(Intent(this@PraloginActivity, LoginActivity::class.java))
            finish()
        }
    }
}