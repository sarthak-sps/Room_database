package com.example.room.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    // we will run all these function on background thread that' why we are using suspend function
    @Insert
    suspend fun insertContact(contact:Contact)

    @Update
    suspend fun updateContact(contact:Contact)

    @Delete
    suspend fun deleteContact(contact:Contact)

    @Query("DELETE FROM contact")
    fun deleteAll()

    @Query("SELECT * FROM contact")
     fun getContact(): LiveData<List<Contact>>


}