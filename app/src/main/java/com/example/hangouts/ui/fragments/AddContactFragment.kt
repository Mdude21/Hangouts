package com.example.hangouts.ui.fragments.add_contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentAddContactBinding
import com.example.hangouts.domain.models.Contact

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private var _binding : FragmentAddContactBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val button: Button = view.findViewById(R.id.fragmentEditContactButtonSave)
        button.setOnClickListener {
//            var contact: Contact
//            contact.address = binding.fragmentEditContactAddress.text.toString()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}