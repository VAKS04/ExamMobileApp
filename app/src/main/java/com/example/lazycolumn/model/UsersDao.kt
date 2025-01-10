package com.example.lazycolumn.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUser(vararg user:Users)

    @Query("SELECT * FROM users WHERE login = :login and password =:password")
    suspend fun isExist(login:String,password:String):Users?

    @Update
    suspend fun updateUser(vararg user:Users)


}