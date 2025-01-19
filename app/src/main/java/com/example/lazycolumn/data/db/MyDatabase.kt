package com.example.lazycolumn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [People::class, Users::class], version = 1)
abstract class DatabaseModel():RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
    abstract fun usersDao(): UsersDao

    companion object {
        fun getDatabase(context: Context): DatabaseModel {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                DatabaseModel::class.java,
                "database"
            ).build()
            return instance
        }
    }
}
