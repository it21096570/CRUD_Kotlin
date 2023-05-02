package com.example.mad_final_exam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.mad_final_exam.Database.DBHandler

class EditProfile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnSearchEP : Button = findViewById(R.id.btnSearchEP)

        btnSearchEP.setOnClickListener {

            val dbHandler = DBHandler(applicationContext)
            var user : List<String> = dbHandler.readAllInfo(findViewById<EditText>(R.id.etUsernameEP).text.toString())

            if (user.isEmpty()){
                Toast.makeText(this, "No User", Toast.LENGTH_LONG).show()
                findViewById<EditText>(R.id.etUsernameEP).text = null

            }
            else{
                Toast.makeText(this, "User Found", Toast.LENGTH_LONG).show()

                findViewById<EditText>(R.id.etUsernameEP).setText(user.get(0).toString())
                findViewById<EditText>(R.id.etDtaeofBirthEP).setText(user.get(1).toString())
                findViewById<EditText>(R.id.etPasswordEP).setText(user.get(2).toString())

                if (user.get(3).toString().equals("Male")){
                    findViewById<RadioButton>(R.id.rbMaleEP).isChecked = true
                }
                else{
                    findViewById<RadioButton>(R.id.rbFemaleEP).isChecked = true

                }

            }

        }
    }
}