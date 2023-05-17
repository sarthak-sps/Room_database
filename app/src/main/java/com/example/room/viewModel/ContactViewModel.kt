package com.example.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.models.Contact
import com.example.room.models.ContactDatabase
import com.example.room.repository.ContactRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application:Application):AndroidViewModel(application) {

     val readAllcontact: LiveData<List<Contact>>
    private val repository: ContactRepo

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepo(contactDao)
        readAllcontact = repository.readAllData
    }

    fun insertContact(contact:Contact)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertContact(contact)
        }
    }
    fun updateContact(contact:Contact)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact:Contact)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()

        }
    }
}