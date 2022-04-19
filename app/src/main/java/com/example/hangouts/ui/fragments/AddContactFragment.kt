package com.example.hangouts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentAddContactBinding
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.viewmodels.AddContactViewModel
import java.util.*

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private lateinit var binding : FragmentAddContactBinding
    private val viewModel : AddContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (binding) {
            fragmentEditContactButtonSave.setOnClickListener {
                val contact = Contact(
                    id = Random().nextLong(),
                    phoneNumber = fragmentEditContactPhoneNumber.text.toString(),
                    firstName = fragmentEditContactFirstName.text.toString(),
                    lastName = fragmentEditContactLastName.text.toString(),
                    email = fragmentEditContactEmailAddress.text.toString(),
                    address = fragmentEditContactAddress.text.toString(),
                    avatar = null
                )
                viewModel.addContact(contact)
                findNavController().navigate(R.id.action_addContactFragment_to_listContactFragment)
            }
        }
    }
}