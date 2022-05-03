package com.example.hangouts.ui.fragments

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentAddContactBinding
import com.example.hangouts.domain.models.Contact
import com.example.hangouts.ui.viewmodels.AddContactViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.util.*
import android.content.ContentResolver


class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()
    private var avatarPath: String? = null
    private var contact: Contact? = null

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

        if (arguments != null && arguments!!.containsKey("info_contact")) {
            contact = arguments?.getSerializable("info_contact") as Contact
        }

        with(binding) {

            if (contact != null)
                fillContact()

            editContactAvatarImageButton.setOnClickListener {
                openGalleryForImage()
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
                        avatar = avatarPath
                    )
                    viewModel.addContact(contact!!)
                    Snackbar.make(view, "Contact is added", Snackbar.LENGTH_SHORT).show()
                } else {
                    contact?.phoneNumber = fragmentEditContactPhoneNumber.text.toString()
                    contact?.address = fragmentEditContactAddress.text.toString()
                    contact?.email = fragmentEditContactEmailAddress.text.toString()
                    contact?.firstName = fragmentEditContactFirstName.text.toString()
                    contact?.lastName = fragmentEditContactLastName.text.toString()
                    if (avatarPath != null)
                        contact?.avatar = avatarPath
                    viewModel.updateContact(contact!!)
                    Snackbar.make(view, "Contact is updated", Snackbar.LENGTH_SHORT).show()
                }
//                viewModel.addContact(contact!!)
//                Snackbar.make(view, "Contact is added", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addContactFragment_to_listContactFragment)
            }
        }
    }

    private fun fillContact(){
        with (binding) {
            fragmentEditContactPhoneNumber.setText(contact?.phoneNumber.toString())
            fragmentEditContactFirstName.setText(contact?.firstName)
            fragmentEditContactLastName.setText(contact?.lastName)
            fragmentEditContactEmailAddress.setText(contact?.email)
            fragmentEditContactAddress.setText(contact?.address)
            if (contact?.avatar != null)
                editContactAvatarImageButton.setImageURI(Uri.parse(contact?.avatar))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == OPERATION_CHOOSE_PHOTO) {
            binding.editContactAvatarImageButton.setImageURI(data?.data)
            val resolver = activity!!.contentResolver
            val uri = data?.data
            resolver.takePersistableUriPermission(uri!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            avatarPath = data.data.toString()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }

    companion object {
        private const val OPERATION_CHOOSE_PHOTO = 2
    }
}