package com.example.hangouts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentAddContactBinding
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.viewmodels.AddContactViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()

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

        var contact : Contact? = null

        if (arguments != null && arguments!!.containsKey("info_contact")) {
            contact = arguments?.getSerializable("info_contact") as Contact
        }

        with(binding) {

            if (contact != null) {
                fragmentEditContactPhoneNumber.setText(contact?.phoneNumber.toString())
                fragmentEditContactFirstName.setText(contact?.firstName)
                fragmentEditContactLastName.setText(contact?.lastName)
                fragmentEditContactEmailAddress.setText(contact?.email)
                fragmentEditContactAddress.setText(contact?.address)
            }

            fragmentEditContactButtonSave.setOnClickListener {
                if (contact?.id == null) {
                    contact = Contact(
                        id = Random().nextLong(),
                        phoneNumber = fragmentEditContactPhoneNumber.text.toString(),
                        firstName = fragmentEditContactFirstName.text.toString(),
                        lastName = fragmentEditContactLastName.text.toString(),
                        email = fragmentEditContactEmailAddress.text.toString(),
                        address = fragmentEditContactAddress.text.toString(),
                        avatar = null
                    )
                }
                else{
                    contact?.phoneNumber = fragmentEditContactPhoneNumber.text.toString()
                    contact?.address = fragmentEditContactAddress.text.toString()
                    contact?.email = fragmentEditContactEmailAddress.text.toString()
                    contact?.firstName = fragmentEditContactFirstName.text.toString()
                    contact?.lastName = fragmentEditContactLastName.text.toString()
                }
                viewModel.addContact(contact!!)
                Snackbar.make(view,"Contact is added", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addContactFragment_to_listContactFragment)
            }
        }
    }
}