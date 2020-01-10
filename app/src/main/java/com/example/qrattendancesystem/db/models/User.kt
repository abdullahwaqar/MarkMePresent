package com.example.qrattendancesystem.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    val local_id : Int? = null,

    @ColumnInfo(name = "_id")
    val _id : String?,

    @ColumnInfo(name = "name")
    val name : String?,

    @ColumnInfo(name = "roll_id")
    val roll_id : String?,

    @ColumnInfo(name = "password")
    val password : String?
)