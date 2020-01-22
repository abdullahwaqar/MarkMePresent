package com.example.qrattendancesystem.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qrattendancesystem.db.models.QrResult

@Dao
interface QrResultDAO {

    @Query("SELECT * FROM qr_result")
    fun getQrResults(): List<QrResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertQrResult(result : QrResult)
}