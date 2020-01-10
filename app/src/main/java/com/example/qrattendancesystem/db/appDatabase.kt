package com.example.qrattendancesystem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qrattendancesystem.db.models.User
import com.example.qrattendancesystem.db.models.doa.UserDAO

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "qr_attendance_db"
        private  var database : AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
            }
            return database
        }
    }

    abstract fun getUserDAO() : UserDAO
}