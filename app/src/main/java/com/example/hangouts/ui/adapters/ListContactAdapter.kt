package com.example.hangouts.ui.fragments.list

import android.annotation.SuppressLint
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.domain.models.DisplayContactItem

class ListContactAdapter() :
    RecyclerView.Adapter<ListContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contacts,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = differ.currentList[position]
        holder.itemView.apply {
            val itemName = findViewById<TextView>(R.id.itemContactsName)
            val itemText = findViewById<TextView>(R.id.itemContactsText)
//            val itemAvatar = findViewById<ImageView>(R.id.itemContactsAvatar)

            itemName.text = contact.firstName + " " + contact.lastName
            itemText.text = contact.phoneNumber

            setOnClickListener{
                onItemClickListener?.let { it(contact) }
            }

        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((Contact) -> Unit)? = null

    fun setOnClickListener(listener: (Contact) -> Unit) {
        onItemClickListener = listener
    }
}