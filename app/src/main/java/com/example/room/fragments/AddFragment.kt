package com.example.room.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.models.Contact
import com.example.room.viewModel.ContactViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class addFragment : Fragment() {
    lateinit var name:TextInputEditText
    lateinit var phone:TextInputEditText
    lateinit var email:TextInputEditText
    lateinit var contactViewModel:ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)




        lateinit var add: Button
        name=view.findViewById(R.id.name)
        phone=view.findViewById(R.id.phone)
        email=view.findViewById(R.id.email)
        add=view.findViewById(R.id.add)

        contactViewModel=ViewModelProvider(this).get(ContactViewModel::class.java)
        add.setOnClickListener{
            insertDataToDatabase()
        }

        return view

    }
   private  fun insertDataToDatabase()
    {
    var name= name.text.toString()
    var phone = phone.text.toString()
    var email = email.text.toString()

        if(inputCheck(name,phone,email))
        {
            val contact = Contact(0,name,phone,email, Date(),1)
            contactViewModel.insertContact(contact)
            // navigate
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_SHORT).show()
        }
    }
  private  fun inputCheck(name:String,phone :String,email:String):Boolean
    {
        return(!(TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(email)) )
    }


}