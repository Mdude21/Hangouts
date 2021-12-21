package com.example.hangouts.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.R
import com.example.hangouts.models.DisplayContactItem
import com.example.hangouts.utils.autoCleared

class ListContactFragment :  Fragment(R.layout.activity_main) {

//    private val viewModel : ListContactFragmentViewModel by viewModels()
//    private var contactAdapter : ListContactAdapter by autoCleared()
    private var recView: RecyclerView? = null

    private val contacts = listOf(
        DisplayContactItem(name = "asd",
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NIKOGDA","Nastya vsegda prava")
        recView?.findViewById<RecyclerView>(R.id.list)
        recView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListContactAdapter(contacts)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recView = null
    }


}