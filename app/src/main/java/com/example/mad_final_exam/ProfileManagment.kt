package com.example.mad_final_exam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.mad_final_exam.Database.DBHandler

class ProfileManagment : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

            if (!validateInputFields()) {
                return@setOnClickListener
            }


            gender = if (findViewById<RadioButton>(R.id.rbMalePM).isChecked){
                "Male"
            } else{
                "Female"
            }

            val dbHandler = DBHandler(applicationContext)

            val success = dbHandler.addInfo(
                findViewById<EditText>(R.id.etUsernamePM).text.toString(),
                findViewById<EditText>(R.id.etDateofbirthPM).text.toString(),
                findViewById<EditText>(R.id.etPasswordPM).text.toString(),
                gender
            )
            Toast.makeText(this, "Data Added Successfully!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, EditProfile::class.java))

            findViewById<EditText>(R.id.etUsernamePM).text = null
            findViewById<EditText>(R.id.etDateofbirthPM).text = null
            findViewById<EditText>(R.id.etPasswordPM).text = null
            findViewById<EditText>(R.id.etUsernamePM).text = null
            findViewById<RadioButton>(R.id.rbMalePM).isChecked = false
            findViewById<RadioButton>(R.id.rbFemalePM).isChecked = false
        }

        val btnListviewPM : Button = findViewById(R.id.btnListviewPM)

        btnListviewPM.setOnClickListener {
            startActivity(Intent(this,UserList::class.java))
        }




    }

    private fun validateInputFields(): Boolean {
        val username = findViewById<EditText>(R.id.etUsernamePM)
        val dob = findViewById<EditText>(R.id.etDateofbirthPM)
        val password = findViewById<EditText>(R.id.etPasswordPM)
        val genderMale = findViewById<RadioButton>(R.id.rbMalePM)
        val genderFemale = findViewById<RadioButton>(R.id.rbFemalePM)

        if (username.text.isNullOrEmpty()) {
            username.error = "Please enter a username"
            return false
        }

        if (dob.text.isNullOrEmpty()) {
            dob.error = "Please enter a date of birth"
            return false
        }

        if (password.text.isNullOrEmpty()) {
            password.error = "Please enter a password"
            return false
        }

        if (!genderMale.isChecked && !genderFemale.isChecked) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }
}