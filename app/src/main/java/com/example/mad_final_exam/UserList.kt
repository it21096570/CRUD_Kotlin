package com.example.mad_final_exam

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_final_exam.Database.DBHandler


class UserList : AppCompatActivity() {

    lateinit var userList: ListView
    lateinit var dataList: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>

    lateinit var db: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userList = findViewById(R.id.lvListviewUL)

        db = DBHandler(applicationContext)

        dataList = db.readAllInfo() as ArrayList<String>

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)

        userList.setAdapter(adapter)

        val user = findViewById<EditText>(R.id.etUsernameUL)

        val btnSearchUL : Button = findViewById(R.id.btnSearchUL)

        btnSearchUL.setOnClickListener {

            var user = user.text.toString()


                var intent = Intent(this, EditProfile::class.java)
                intent.putExtra("user", user)
                startActivity(intent)

            }

        }

    }
