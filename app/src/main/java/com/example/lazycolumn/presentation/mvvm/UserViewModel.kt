package com.example.lazycolumn.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.R
import com.example.lazycolumn.model.MyDatabase
import com.example.lazycolumn.model.Users
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {
    private val _isLogin = mutableStateOf(false)
    val isLogin :State<Boolean> = _isLogin

    private val _user = mutableStateOf<Users?>(null)
    var user : State<Users?> = _user

    private fun changeItem(newUser:Users){
        _user.value = newUser
    }

    fun updateLogin(context: Context, newLogin: String,){
        viewModelScope.launch {
            val database = MyDatabase.getDatabase(context)
            val users = database.usersDao()
            var user:Users

            if (newLogin.isNotEmpty()){
                user = Users(
                    id = _user.value!!.id,
                    login = newLogin,
                    password = _user.value!!.password
                )
                users.updateUser(user)
            }
        }
    }


    fun updatePassword(context: Context, newPassword: String){
        viewModelScope.launch {
            val database = MyDatabase.getDatabase(context)
            val users = database.usersDao()
            var user: Users

            if (newPassword.isNotEmpty()) {
                user = Users(
                    id = _user.value!!.id,
                    login = _user.value!!.login,
                    password = newPassword
                )
                users.updateUser(user)
            }
        }
    }


    private fun isPasswordsMatch(password1: String, password2: String):Boolean{
        return password1 == password2
    }

    fun registration(context: Context, login: String, password1: String, password2: String){
        viewModelScope.launch {
            if (isPasswordsMatch(
                    password1 = password1,
                    password2 = password2
                ) && login.isNotEmpty() && password1.isNotEmpty()) {
                val database = MyDatabase.getDatabase(context)
                val users = database.usersDao()

                users.insertUser(
                    Users(login = login, password = password1)
                )

                Toast.makeText(
                    context,
                    R.string.toast_registered,
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(
                    context,
                    R.string.toast_check_your_data,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun authorization(context: Context, login:String, password:String){
        viewModelScope.launch {
            val dataBase = MyDatabase.getDatabase(context = context)
            val usersData = dataBase.usersDao()

            if (login.isEmpty() || password.isEmpty()){
                Toast.makeText(
                    context,
                    R.string.toast_check_your_data,
                    Toast.LENGTH_SHORT
                ).show()
            }
            val user = usersData.isExist(login,password)

            if (user == null){
                Toast.makeText(
                    context,
                    R.string.toast_user_not_found,
                    Toast.LENGTH_SHORT).show()
            }else{
                changeItem(user)
                _isLogin.value = true
            }
        }
    }
}