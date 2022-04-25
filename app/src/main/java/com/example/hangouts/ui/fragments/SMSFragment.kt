package com.example.hangouts.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentSmsBinding
import com.example.hangouts.domain.models.Message
import com.example.hangouts.ui.adapters.SMSAdapter
import java.util.*

class SMSFragment : Fragment(R.layout.fragment_sms) {
    private lateinit var smsAdapter: SMSAdapter
    private lateinit var binding: FragmentSmsBinding

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

        smsAdapter = SMSAdapter(this.context!!)

        binding.smsRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = smsAdapter
        }

        binding.sendButton.setOnClickListener {
                val message = Message(
                    id = Random().nextLong(),
                    message = binding.inputTextView.text.toString(),
                    time = System.currentTimeMillis(),
                    user = "Me",
                    type = 1
                )
                smsAdapter.addMessage(message)
                smsAdapter.update()
                binding.inputTextView.setText("")
                hideKeyboard()
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
}