package com.example.mad_final_exam.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.text.Selection


class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Database.db"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${UserProfile.Users.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${UserProfile.Users.COLUMN1} TEXT," +
                "${UserProfile.Users.COLUMN2} TEXT," +
                "${UserProfile.Users.COLUMN3} TEXT," +
                "${UserProfile.Users.COLUMN4} TEXT, +)"


                private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserProfile.Users.TABLE_NAME}"

    fun addInfo(username: String, dob: String, password: String, gender: String) {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(UserProfile.Users.COLUMN1, username)
            put(UserProfile.Users.COLUMN2, dob)
            put(UserProfile.Users.COLUMN3, password)
            put(UserProfile.Users.COLUMN4, gender)

        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(UserProfile.Users.TABLE_NAME, null, values)
    }

    fun updateInfo(username: String, dob: String, password: String, gender: String): Boolean{

        val db = writableDatabase

// New value for one column
        val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(UserProfile.Users.COLUMN2, dob)
            put(UserProfile.Users.COLUMN3, password)
            put(UserProfile.Users.COLUMN4, gender)
        }

// Which row to update, based on the title
        val selection = "${UserProfile.Users.COLUMN1} LIKE ?"
        val selectionArgs = arrayOf(username)
        val count = db.update(
            UserProfile.Users.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        return count > 0
    }

    fun deleteInfo(username: String,){

        val db = writableDatabase


        // Define 'where' part of query.
        val selection = "${UserProfile.Users.COLUMN1} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(username)
        // Issue SQL statement.
        val deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs)

    }

    fun readAllInfo():List<String>{

        val username = "test";

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            BaseColumns._ID,
            UserProfile.Users.COLUMN1,
            UserProfile.Users.COLUMN2,
            UserProfile.Users.COLUMN3,
            UserProfile.Users.COLUMN4
        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${UserProfile.Users.COLUMN1} = ?"
        val selectionArgs = arrayOf(username)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${UserProfile.Users.COLUMN1} ASC"

        val cursor = db.query(
            UserProfile.Users.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val usernames = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val user = getString(getColumnIndexOrThrow(UserProfile.Users.COLUMN1))
                usernames.add(user)
            }
        }
        cursor.close()
        return usernames
    }

    fun readAllInfo(username: String):List<String>{

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            BaseColumns._ID,
            UserProfile.Users.COLUMN1,
            UserProfile.Users.COLUMN2,
            UserProfile.Users.COLUMN3,
            UserProfile.Users.COLUMN4
        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${UserProfile.Users.COLUMN1} = LIKE ?"
        val selectionArgs = arrayOf(username)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${UserProfile.Users.COLUMN1} ASC"

        val cursor = db.query(
            UserProfile.Users.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val userInfo = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val user = getString(getColumnIndexOrThrow(UserProfile.Users.COLUMN1))
                val dob = getString(getColumnIndexOrThrow(UserProfile.Users.COLUMN2))
                val pass = getString(getColumnIndexOrThrow(UserProfile.Users.COLUMN3))
                val gender = getString(getColumnIndexOrThrow(UserProfile.Users.COLUMN4))

                userInfo.add(user)//0
                userInfo.add(dob)//1
                userInfo.add(pass)//2
                userInfo.add(gender)//3

            }
        }
        cursor.close()
        return userInfo
    }

}