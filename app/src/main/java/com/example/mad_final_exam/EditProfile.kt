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

        var value = intent.getStringExtra("user")

        val user1 = value!!.toString()


        //var username = findViewById<EditText>(R.id.etUsernameEP)
        //var dob = findViewById<EditText>(R.id.etDtaeofBirthEP)
        //var password = findViewById<EditText>(R.id.etPasswordEP)
        //var male = findViewById<RadioButton>(R.id.rbMaleEP)
        //var female = findViewById<RadioButton>(R.id.rbFemaleEP)

        var gender : String

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)



            val dbHandler = DBHandler(applicationContext)
            var user : List<String> = dbHandler.readAllInfo(user1.toString())

            if (user.isEmpty()){
                Toast.makeText(this, "No User", Toast.LENGTH_LONG).show()
                findViewById<EditText>(R.id.etUsernameEP).text = null
                var intent = Intent(this, UserList::class.java)
                startActivity(intent)

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



        val btnEditEP : Button = findViewById(R.id.btnEditEP)

        btnEditEP.setOnClickListener {

            gender = if (findViewById<RadioButton>(R.id.rbMaleEP).isChecked){
                "Male"
            } else{
                "Female"
            }

            val dbHandler = DBHandler(applicationContext)

            var status : Boolean = dbHandler.updateInfo(
                findViewById<EditText>(R.id.etUsernameEP).text.toString(),
                findViewById<EditText>(R.id.etDtaeofBirthEP).text.toString(),
                findViewById<EditText>(R.id.etPasswordEP).text.toString(),
                gender
            )
            if (status){
                Toast.makeText(this, "User Updated!", Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(this, "User Not Updated!", Toast.LENGTH_LONG).show()

            }

        }

        val btnDeleteEP : Button = findViewById(R.id.btnDeleteEP)

        btnDeleteEP.setOnClickListener{
            val dbHandler = DBHandler(applicationContext)
            dbHandler.deleteInfo(findViewById<EditText>(R.id.etUsernameEP).text.toString())

            Toast.makeText(this, "User Deleted!", Toast.LENGTH_LONG).show()

            findViewById<EditText>(R.id.etUsernameEP).text = null
            findViewById<EditText>(R.id.etDtaeofBirthEP).text = null
            findViewById<EditText>(R.id.etPasswordEP).text = null
            findViewById<EditText>(R.id.etUsernameEP).text = null
            findViewById<RadioButton>(R.id.rbMaleEP).isChecked = false
            findViewById<RadioButton>(R.id.rbFemaleEP).isChecked = false

        }
    }
}