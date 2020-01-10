package com.example.qrattendancesystem.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    var local_id : Int? = null,

    @ColumnInfo(name = "_id")
    var _id : String?,

    @ColumnInfo(name = "name")
    var name : String?,

    @ColumnInfo(name = "roll_id")
    var roll_id : String?,

    @ColumnInfo(name = "password")
    var password : String?
)