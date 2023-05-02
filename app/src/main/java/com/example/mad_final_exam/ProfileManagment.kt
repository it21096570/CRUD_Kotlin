package com.example.mad_final_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.mad_final_exam.Database.DBHandler

class ProfileManagment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_managment)

        var gender: String

        val btnUpdateProfilePM : Button = findViewById(R.id.btnUpdateProfilePM)

        btnUpdateProfilePM.setOnClickListener {
            startActivity(Intent(this,EditProfile::class.java))
        }

        val btnAddPM : Button = findViewById(R.id.btnAddPM)

        btnAddPM.setOnClickListener {

            if (findViewById<RadioButton>(R.id.rbMalePM).isChecked){
                gender = "Male"
            }
            else{
                gender = "Female"
            }


            val dbHandler = DBHandler(applicationContext)
            val success = dbHandler.addInfo(
                findViewById<EditText>(R.id.etUsernamePM).text.toString(),
                findViewById<EditText>(R.id.etDateofbirthPM).text.toString(),
                findViewById<EditText>(R.id.etUsernamePM).text.toString(),
                gender
                )
            Toast.makeText(this, "Data Added Successfully! - UserID : $success", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,EditProfile::class.java))



        }
    }
}