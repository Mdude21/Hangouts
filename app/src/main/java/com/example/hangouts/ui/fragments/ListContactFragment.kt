package com.example.hangouts.ui.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.domain.models.DisplayContactItem
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListContactFragment :  Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contacts = listOf(
            DisplayContactItem(
                name = "asd",
                text = "qwe",
                avatar = "zxc"),
            DisplayContactItem(
                name = "Ivan Ivanov",
                text = "hi",
                avatar = "lol"
            ),
            DisplayContactItem(
                name = "Petr Petrov",
                text = "bye",
                avatar = "lol"
            )
        )

        val recView: RecyclerView? = view.findViewById(R.id.list)
        recView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListContactAdapter(contacts)
        }
        recView?.addOnItemTouchListener(RecyclerItemClickListener(recView,
        object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                findNavController().navigate(R.id.action_listFragment_to_infoContactFragment)
            }
        }))
        val button: FloatingActionButton? = view.findViewById(R.id.fragmentListFloatingActionButton)
        button?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addContactFragment)
        }
    }


}