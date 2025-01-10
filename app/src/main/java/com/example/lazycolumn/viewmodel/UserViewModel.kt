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

    fun updateData(newData:Users,context: Context){
        viewModelScope.launch {
            val database = MyDatabase.getDatabase(context)
            val users = database.usersDao()
            val user:Users

            if (newData.login.isNotEmpty()){
                user = Users(
                    id = _user.value!!.id,
                    login = newData.login,
                    password = _user.value!!.password
                )
                users.updateUser(user)

            }
            else if (newData.password.isNotEmpty()){
                user = Users(
                    id = _user.value!!.id,
                    login = _user.value!!.login,
                    password = newData.password
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
                )) {
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
                    R.string.toast_password_doesnt_match,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun authorization(context: Context, login:String,password:String){
        viewModelScope.launch {
            val dataBase = MyDatabase.getDatabase(context = context)
            val usersData = dataBase.usersDao()

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