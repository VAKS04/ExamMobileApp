package com.example.lazycolumn.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazycolumn.R
import com.example.lazycolumn.model.Country
import com.example.lazycolumn.model.MyDatabase
import com.example.lazycolumn.model.PeopleData
import com.example.lazycolumn.model.Users
import kotlinx.coroutines.launch
import org.json.JSONArray

class PeopleViewModel :ViewModel(){
    private val _data = mutableStateOf<List<PeopleData>>(emptyList())
    val data: State<List<PeopleData>> = _data

    private val _filterKey = mutableStateOf(Country.ALL)
    val filterKey :State<Country> = _filterKey

    private val _isLogin = mutableStateOf(true)
    val isLogin :State<Boolean> = _isLogin

    private val _person = mutableStateOf<PeopleData?>(null)
    val person :State<PeopleData?> = _person

    private fun isPasswordsMatch(password1: String, password2: String):Boolean{
        return password1 == password2
    }

    fun updateFilterKey(country:Country){
        _filterKey.value = country
    }

    fun authorization(context: Context, login:String,password:String){
        viewModelScope.launch {
            val dataBase = MyDatabase.getDatabase(context = context )
            val usersData = dataBase.usersDao()

            if (usersData.isExist(login,password) == null){
                Toast.makeText(
                    context,
                    R.string.toast_user_not_found,
                    Toast.LENGTH_SHORT).show()
            }else{
                _isLogin.value = true
            }
        }
    }

    fun registration(context: Context, login: String, password1: String,password2: String){
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

    fun fetchDataFromJson(context: Context){
        viewModelScope.launch {
            val jsonString = loadJsonFromAssets(context,"people.json")
            val jsonArray = JSONArray(jsonString)

            val peopleList = mutableListOf<PeopleData>()
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val country = Country.fromName(jsonObject.getString("country"))

                if ( filterKey.value == Country.ALL || filterKey.value == country ){
                    val person = PeopleData(
                        id = jsonObject.getInt("id"),
                        name = jsonObject.getString("name"),
                        description = jsonObject.getString("description"),
                        image = jsonObject.getString("image"),
                        country = country
                    )
                    peopleList.add(person)
                }
            }
            _data.value = peopleList
        }
    }

    fun fetchPersonFromJson(context: Context,id:Int){
        viewModelScope.launch {
            val jsonString = loadJsonFromAssets(context,"people.json")
            val jsonArray = JSONArray(jsonString)

            val jsonObject = jsonArray.getJSONObject(id)
            val country = Country.fromName(jsonObject.getString("country"))

            _person.value = PeopleData(
                id = jsonObject.getInt("id"),
                name = jsonObject.getString("name"),
                description = jsonObject.getString("description"),
                image = jsonObject.getString("image"),
                country = country
            )
        }
    }

    private fun loadJsonFromAssets(context: Context, fileName:String):String{
        return context.assets.open(fileName).bufferedReader().use{it.readText()}
    }
}