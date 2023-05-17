package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.models.Contact
import com.example.room.models.ContactDao

class ContactRepo(private val contactDao:ContactDao) {

    val  readAllData:LiveData<List<Contact>> = contactDao.getContact()

    suspend  fun insertContact(contact:Contact)
    {
        contactDao.insertContact(contact)
    }

    suspend  fun updateContact(contact:Contact)
    {
        contactDao.updateContact(contact)
    }

    suspend  fun deleteContact(contact:Contact)
    {
        contactDao.deleteContact(contact)
    }
    suspend fun  deleteAll()
    {
        contactDao.deleteAll()
    }

}