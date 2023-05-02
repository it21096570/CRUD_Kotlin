package com.example.mad_final_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnRegisterH : Button = findViewById(R.id.btnRegisterH)

        btnRegisterH.setOnClickListener {
            startActivity(Intent(this,ProfileManagment::class.java))
        }
    }
}