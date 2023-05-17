package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.room.models.Contact
import com.example.room.models.ContactDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
//    lateinit var database:ContactDatabase // creating variable of type database class
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//     setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
        // here we are  building  object of our database class
        // by calling a database builder class and passing the application context , class name,name of our database then call build method to
        // build the object of database
//        database= Room.databaseBuilder(applicationContext,
//            ContactDatabase::class.java, "contactDB"
//        ).build() // it will return object of database

//        database=ContactDatabase.getDatabase(this)

//        GlobalScope.launch {
//            database.contactDao().insertContact(Contact(0,"Sarthak","9670446703", "Sarthak@softprodigy.com",Date(),1))
//        }

    }

}