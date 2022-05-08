package com.example.hangouts.ui.fragments

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentSmsBinding
import com.example.hangouts.domain.models.Message
import com.example.hangouts.ui.adapters.SMSAdapter
import com.example.hangouts.ui.viewmodels.SMSViewModel
import java.util.*

class SMSFragment : Fragment(R.layout.fragment_sms) {
    private lateinit var smsAdapter: SMSAdapter
    private lateinit var binding: FragmentSmsBinding
    private val viewModel: SMSViewModel by viewModels()
    private val args: SMSFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = args.contact

        smsAdapter = SMSAdapter(this.context!!)

        viewModel.getAllMessages(contact.id!!)

        viewModel.messageList.observe(viewLifecycleOwner, {
            smsAdapter.addList(it)
            smsAdapter.update()

        })



        binding.smsRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = smsAdapter
            smoothScrollToPosition(smsAdapter.itemCount)
        }

        binding.sendButton.setOnClickListener {
            if (binding.inputTextView.text.toString() != "") {
                val message = Message(
                    id = Random().nextLong(),
                    message = binding.inputTextView.text.toString(),
                    time = System.currentTimeMillis(),
                    user = "Me",
                    type = 1,
                    contactId = contact.id!!
                )
                viewModel.addMessage(message)
                smsAdapter.addMessage(message)
                smsAdapter.update()
                binding.inputTextView.setText("")
                hideKeyboard()
                sendSms(contact.phoneNumber.toString(), message.message!!)
                binding.smsRecycler.smoothScrollToPosition(smsAdapter.itemCount)
            }
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            activity!!.currentFocus!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun sendSms(phoneNumber: String, message: String) {
        if (ContextCompat.checkSelfPermission(
                activity!!,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!, arrayOf(android.Manifest.permission.SEND_SMS),
                0
            )

        } else {
            val smsManager = SmsManager.getDefault() as SmsManager
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        }
    }
}