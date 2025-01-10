package com.example.lazycolumn.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeopleDao {

    @Insert
    suspend fun insertAll(vararg person: People)

    @Delete
    suspend fun deletePerson(person: People)

    @Query("SELECT * FROM people")
    suspend fun getAll():List<People>

    @Query("SELECT * from people WHERE id= :idP")
    suspend fun getOne(idP:Int):People
}