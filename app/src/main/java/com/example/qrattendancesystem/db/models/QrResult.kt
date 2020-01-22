package com.example.qrattendancesystem.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qr_result")
data class QrResult(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "_id")
    var _id : String?,

    @ColumnInfo(name = "attendance_id")
    var attendance_id: String?,

    @ColumnInfo(name = "class_name")
    var class_name: String?,

    @ColumnInfo(name = "teacher_name")
    var teacher_name: String?,

    @ColumnInfo(name = "class_date_time")
    var class_date_time : String?
)