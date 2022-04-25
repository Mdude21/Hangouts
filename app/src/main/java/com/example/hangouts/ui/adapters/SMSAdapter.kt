package com.example.hangouts.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.databinding.ItemInputSmsBinding
import com.example.hangouts.databinding.ItemOutputSmsBinding
import com.example.hangouts.domain.models.Message
import java.text.SimpleDateFormat
import java.util.*

private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_OTHER_MESSAGE = 2

class SMSAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = mutableListOf<Message>()

    fun addMessage(message: Message) {
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].type == 1)
            VIEW_TYPE_MY_MESSAGE
        else
            VIEW_TYPE_OTHER_MESSAGE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val bindingMyMessageViewHolder: ItemInputSmsBinding = ItemInputSmsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val bindingOtherMessageViewHolder: ItemOutputSmsBinding = ItemOutputSmsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return when (viewType) {
            VIEW_TYPE_MY_MESSAGE -> MyMessageViewHolder(bindingMyMessageViewHolder)
            VIEW_TYPE_OTHER_MESSAGE -> OtherMessageViewHolder(bindingOtherMessageViewHolder)
            else -> throw error("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyMessageViewHolder -> holder.bind(messages[position])
            is OtherMessageViewHolder -> holder.bind(messages[position])
            else -> error("Unknown view holder")
        }
    }

    override fun getItemCount(): Int = messages.size
}

class MyMessageViewHolder(private val binding: ItemInputSmsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: Message) {
        binding.txtMyMessage.text = message.message
        binding.txtMyMessageTime.text = com.example.hangouts.utils.DateUtils.fromMillisToTimeString(message.time)
    }
}

class OtherMessageViewHolder(private val binding: ItemOutputSmsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: Message) {
        with(binding) {
            txtOtherMessage.text = message.message
            txtOtherMessageTime.text = com.example.hangouts.utils.DateUtils.fromMillisToTimeString(message.time)
            txtOtherUser.text = message.user
        }
    }
}