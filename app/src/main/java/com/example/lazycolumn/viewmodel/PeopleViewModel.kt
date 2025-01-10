package com.example.lazycolumn.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.model.Country
import com.example.lazycolumn.model.PeopleData
import kotlinx.coroutines.launch
import org.json.JSONArray

class PeopleViewModel :ViewModel(){
    private val _data = mutableStateOf<List<PeopleData>>(emptyList())
    val data: State<List<PeopleData>> = _data

    private val _filterKey = mutableStateOf(Country.ALL)
    val filterKey :State<Country> = _filterKey

    private val _person = mutableStateOf<PeopleData?>(null)
    val person :State<PeopleData?> = _person

    fun updateFilterKey(country:Country){
        _filterKey.value = country
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