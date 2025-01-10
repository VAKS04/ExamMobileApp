package com.example.lazycolumn.model

import kotlinx.coroutines.flow.Flow


//class DatabaseRepository(private val peopleDao:PeopleDao){
//
//    val allPeople: Flow<List<People>> = peopleDao.getAll()
//
//    suspend fun addPerson(person: People){
//        peopleDao.insertAll(person)
//    }
//    suspend fun showAllPerson():List<People>{
//        return peopleDao.getAll()
//    }
//}