package com.example.lazycolumn.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.model.MyDatabase
import com.example.lazycolumn.model.People
import com.example.lazycolumn.model.Users
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {
    private val _user = mutableStateOf<Users?>(null)
    var user : State<Users?> = _user

    fun updateUserData(newUser:Users){
        _user.value = newUser
    }



    fun changeLogin(newLogin:String){
        _login = newLogin
    }

    fun chagePassword(newPassword){
        _password = newPassword
    }
}


