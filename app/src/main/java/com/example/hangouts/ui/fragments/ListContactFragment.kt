package com.example.hangouts.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentListBinding
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.adapters.ListContactAdapter
import com.example.hangouts.ui.viewmodels.ListContactFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListContactFragment :  Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel : ListContactFragmentViewModel by viewModels()
    private lateinit var listAdapter : ListContactAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
//            readContact()

        listAdapter = ListContactAdapter()
        viewModel.getContactsFromDb()
        viewModel.contactList.observe(viewLifecycleOwner) {
            listAdapter.differ.submitList(it)
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }

        listAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("contact", it)
            }
            findNavController().navigate(R.id.action_listContactFragment_to_infoContactFragment, bundle)
        }

        val button: FloatingActionButton? = view.findViewById(R.id.fragmentListFloatingActionButton)
        button?.setOnClickListener {
            findNavController().navigate(R.id.action_listContactFragment_to_addContactFragment)
        }
    }

    private fun loadContact() {
        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity!!, Array(1) { Manifest.permission.READ_CONTACTS}, 111)
        }
        else
            readContact()
    }

    private fun readContact() {

        val cols = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        ).toTypedArray()

        val rs = activity!!.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        while (rs?.moveToNext()!!) {
            val contact = Contact(
                id = rs.getString(2).toLong(),
                phoneNumber = rs.getString(1),
                firstName = rs.getString(0),
                lastName = "",
                email = null,
                address = null,
                avatar = null
            )
            viewModel.addContactFromContactList(contact)
        }
    }

}