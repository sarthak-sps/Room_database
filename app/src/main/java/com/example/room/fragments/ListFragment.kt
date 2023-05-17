package com.example.room.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.ContactListAdapter
import com.example.room.R
import com.example.room.models.Contact
import com.example.room.viewModel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class listFragment : Fragment() {
    lateinit var listViewModel:ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  view= inflater.inflate(R.layout.fragment_list, container, false)

        // Recycler View




        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ContactListAdapter(requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        adapter.onItemClickListener = { position, bodyType ->
            val bundle = Bundle()
            bundle.putString("name", bodyType.name)
            bundle.putString("phone", bodyType.phone)
            bundle.putString("email", bodyType.email)
            bundle.putLong("id", bodyType.id)
            Log.d("Sarthak", "onViewCreated: ${bundle.getString("name", bodyType.name)}")
            findNavController().navigate(R.id.action_listFragment_to_updatefragment, bundle)

        }
        adapter.onItemClickDelete = { position, bodyType ->
            val builder = AlertDialog.Builder(requireContext())

            builder.setPositiveButton("Yes") { _, _ ->
                listViewModel.deleteContact(bodyType)
                Toast.makeText(
                    requireContext(),
                    "Successfully deleted ${bodyType.name}",
                    Toast.LENGTH_SHORT
                ).show()

            }


            builder.setNegativeButton("No") { _, _ ->

            }
            builder.setTitle("Delete ${bodyType.name}")
            builder.setMessage("Are you sure you want to delete ${bodyType.name}")
            builder.create().show()
        }


        listViewModel=ViewModelProvider(this).get(ContactViewModel::class.java)
        listViewModel.readAllcontact.observe(viewLifecycleOwner, Observer{ contact->
            adapter.setData(contact)
        })
        lateinit var floatButton:FloatingActionButton

        floatButton= view.findViewById(R.id.floatingActionButton)
        floatButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }
    private fun deleteContact()
    {

    }



}