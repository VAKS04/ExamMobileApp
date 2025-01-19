package com.example.lazycolumn.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "login")val login : String,
    @ColumnInfo(name = "password")val password: String
)