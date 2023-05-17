package com.example.room.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.models.Contact
import com.example.room.viewModel.ContactViewModel
import java.util.*


class updatefragment : Fragment() {
   lateinit var nameField: TextView
 lateinit   var phoneField: TextView
   lateinit var  emailField: TextView
  lateinit var  updateButton: Button
  lateinit var  delete:ImageView

  lateinit var  viewModel:ContactViewModel

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
        val view= inflater.inflate(R.layout.fragment_updatefragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =ViewModelProvider(this).get(ContactViewModel::class.java)
         nameField= view.findViewById(R.id.updatename)
         phoneField = view.findViewById(R.id.updatephone)
         emailField = view.findViewById(R.id.updateemail)
         updateButton =view.findViewById(R.id.update)
//        delete =view.findViewById(R.id.deleteIcon)
        val name=arguments?.getString("name")
        val phone=arguments?.getString("phone")
        val email=arguments?.getString("email")
        val id=arguments?.getLong("id")
        nameField.text = name.toString()
        phoneField.text=phone.toString()
        emailField.text = email.toString()
        Log.d("Sarthak", "onCreateView:${name} ")
        updateButton.setOnClickListener{
            updateContact()
        }
    }
    private fun updateContact()
    {
        val updateName= nameField.text.toString()
        val updatePhone=phoneField.text.toString()
        val updateEmail=emailField.text.toString()

        if(inputCheck(updateName,updatePhone,updateEmail))
        {
            val contact = arguments?.let { Contact(it.getLong("id"),updateName,updatePhone,updateEmail, Date(),1) }
            if (contact != null) {
                viewModel.updateContact(contact)
            }
            // navigate
            findNavController().navigate(R.id.action_updatefragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"updated successfully", Toast.LENGTH_SHORT).show()
        }

    }
    private  fun inputCheck(updateName:String,updatePhone :String,updateEmail:String):Boolean
    {
        return(!(TextUtils.isEmpty(updateName) && TextUtils.isEmpty(updatePhone) && TextUtils.isEmpty(updateEmail)) )
    }




}