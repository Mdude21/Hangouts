package com.example.hangouts.ui.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentInfoContactBinding
import com.example.hangouts.ui.viewmodels.InfoContactViewModel

class InfoContactFragment : Fragment(R.layout.fragment_info_contact) {
    private lateinit var binding: FragmentInfoContactBinding
    private val viewModel : InfoContactViewModel by viewModels()
    private val args: InfoContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact = args.contact

        with (binding) {
            infoContactAddress.text = contact.address
            infoContactFirstName.text = contact.firstName
            infoContactLastName.text = contact.lastName
            infoContactEmail.text = contact.email
            infoContactPhoneNubmer.text = contact.phoneNumber.toString()

            infoContactDeleteButton.setOnClickListener {
                viewModel.deleteContact(contact)
                findNavController().navigate(R.id.action_infoContactFragment_to_listContactFragment)
            }

            infoContactEditButton.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable("info_contact", contact)
                }
                findNavController().navigate(R.id.action_infoContactFragment_to_addContactFragment, bundle)
            }

            infoContactCallButton.setOnClickListener {
                call(contact.phoneNumber.toString())
            }
        }
    }

    private fun call(phoneNumber : String) {
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.CALL_PHONE),
                0)

        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }

}