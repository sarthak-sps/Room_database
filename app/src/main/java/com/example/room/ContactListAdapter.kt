package com.example.room

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.room.models.Contact

class ContactListAdapter(val context:Context):RecyclerView.Adapter<ContactListAdapter.MyViewHolder>() {

    private var contactList= emptyList<Contact>()
    var onItemClickListener: ((position: Int, bodyType: Contact) -> Unit)? = null
    var onItemClickDelete:((position:Int,bodyType:Contact)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contactlist, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=contactList[position]
        holder.nameText.text=currentItem.name
        holder.phoneText.text=currentItem.phone
        holder.emailText.text=currentItem.email
//        holder.card.setOnClickListener {
//            Toast.makeText(context,"${currentItem.name}",Toast.LENGTH_SHORT).show()
//            onItemClickListener!!.invoke(position,currentItem)
//        }
        holder.edit.setOnClickListener {
           Toast.makeText(context,"${currentItem.name}",Toast.LENGTH_SHORT).show()
            onItemClickListener!!.invoke(position,currentItem)
        }
        holder.delete.setOnClickListener {
            onItemClickDelete!!.invoke(position,currentItem)
        }

    }

    class MyViewHolder(private val itemView:View):RecyclerView.ViewHolder(itemView){
        var nameText:TextView= itemView.findViewById(R.id.contactName)
        var phoneText:TextView= itemView.findViewById(R.id.phonetext)
        var emailText:TextView= itemView.findViewById(R.id.emailtext)
//        var card:CardView= itemView.findViewById(R.id.cardId)
        var edit:ImageView= itemView.findViewById(R.id.editIcon)
        var delete:ImageView = itemView.findViewById(R.id.deleteIcon)

    }
    fun setData(contact:List<Contact>)
    {
        this.contactList=contact
        notifyDataSetChanged()
    }
}