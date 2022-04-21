package com.example.hangouts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentSmsBinding
import com.example.hangouts.ui.adapters.SMSAdapter

class SMSFragment : Fragment(R.layout.fragment_sms) {
    private lateinit var smsAdapter : SMSAdapter
    private lateinit var binding : FragmentSmsBinding

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
    }
}