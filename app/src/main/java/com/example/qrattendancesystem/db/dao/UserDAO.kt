package com.example.qrattendancesystem.db.models.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qrattendancesystem.db.models.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM User")
    fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}