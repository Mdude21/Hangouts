package com.example.hangouts.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangouts.R
import com.example.hangouts.databinding.FragmentListBinding
import com.example.hangouts.ui.adapters.ListContactAdapter
import com.example.hangouts.ui.viewmodels.ListContactFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.DividerItemDecoration

class ListContactFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListContactFragmentViewModel by viewModels()
    private lateinit var listAdapter: ListContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListContactAdapter()
        viewModel.getContactsFromDb()
        viewModel.contactList.observe(viewLifecycleOwner) {
            listAdapter.differ.submitList(it)
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }

        listAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("contact", it)
            }
            findNavController().navigate(
                R.id.action_listContactFragment_to_infoContactFragment,
                bundle
            )
        }

        val button: FloatingActionButton? = view.findViewById(R.id.fragmentListFloatingActionButton)
        button?.setOnClickListener {
            findNavController().navigate(R.id.action_listContactFragment_to_addContactFragment)
        }
    }

}