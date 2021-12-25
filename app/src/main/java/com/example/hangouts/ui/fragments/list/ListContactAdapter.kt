package com.example.hangouts.ui.fragments.list

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.domain.models.DisplayContactItem

class ListContactAdapter(private val dataSet: List<DisplayContactItem>) :
    RecyclerView.Adapter<ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val infoCallback = {item: DisplayContactItem, position: Int ->
            infoItem(item,position)
        }
        return ViewHolder(inflater, parent, infoCallback)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val currentItem : DisplayContactItem = dataSet[position]
        viewHolder.bind(currentItem)
    }

    private fun infoItem(item: DisplayContactItem, position: Int) {
        val newItems = dataSet.toMutableList().apply {
            remove(item)
        }
//        dataSet = newItems
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup, onItemClick: (item : DisplayContactItem, position: Int) -> Unit) :  RecyclerView.ViewHolder(inflater.inflate(R.layout.item_contacts, parent, false)) {
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

open class RecyclerItemClickListener(recyclerView: RecyclerView, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
    private var mGestureDetector: GestureDetector = GestureDetector(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {}
    })

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}