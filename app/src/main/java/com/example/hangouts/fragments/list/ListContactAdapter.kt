package com.example.hangouts.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.models.DisplayContactItem



class ListContactAdapter(private val dataSet: List<DisplayContactItem>) :
    RecyclerView.Adapter<ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem : DisplayContactItem = dataSet[position]
        viewHolder.bind(currentItem)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :  RecyclerView.ViewHolder(inflater.inflate(R.layout.item_contacts, parent, false)) {
    private var name: TextView? = null
    private var text: TextView? = null
    private var avatar: ImageView? = null

    init {
        // Define click listener for the ViewHolder's View.
        name = itemView.findViewById(R.id.itemContactsName)
        text = itemView.findViewById(R.id.itemContactsText)
        avatar = itemView.findViewById(R.id.itemContactsAvatar)
    }

    fun bind(contactItem: DisplayContactItem) {
        name?.text = contactItem.name
        text?.text = contactItem.text
//            avatar?.setImageResource(R.drawable.avatar)
    }
}