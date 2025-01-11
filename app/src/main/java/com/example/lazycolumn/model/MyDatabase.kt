package com.example.lazycolumn.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [People::class,Users::class], version = 1)
abstract class MyDatabase():RoomDatabase() {
//    abstract fun peopleDao(): PeopleDao

    abstract fun usersDao():UsersDao

    companion object {
        fun getDatabase(context: Context):MyDatabase{
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "database"
            ).build()
            return instance
        }
    }
}
