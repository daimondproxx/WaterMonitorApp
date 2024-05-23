package com.example.watermonitorapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.watermonitorapp.data.dao.UserParamsDao
import com.example.watermonitorapp.data.dao.WaterControlDao
import com.example.watermonitorapp.data.entity.UserParams
import com.example.watermonitorapp.data.entity.WaterControl


@Database(
    entities = [
    WaterControl::class,
    UserParams::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun waterControlDao() : WaterControlDao

    abstract fun userParamsDao(): UserParamsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context = context.applicationContext,
                     klass = AppDatabase::class.java,
                     name = "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}