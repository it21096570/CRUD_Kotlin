package com.example.mad_final_exam.Database

import android.provider.BaseColumns

object UserProfile {
    // Table contents are grouped together in an anonymous object.
    object Users : BaseColumns {
        const val TABLE_NAME = "UserInfo"
        const val COLUMN1 = "userName"
        const val COLUMN2 = "dateOfBirth"
        const val COLUMN3 = "password"
        const val COLUMN4 = "gender"


    }
}