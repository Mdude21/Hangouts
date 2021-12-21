package com.example.hangouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangouts.fragments.list.ListContactAdapter
import com.example.hangouts.models.DisplayContactItem

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val contacts = listOf(
        DisplayContactItem(name = "Sidor Sidorov",
            text = "isemez",
            avatar = "lol"),
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recView: RecyclerView = findViewById(R.id.list)
        recView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListContactAdapter(contacts)
        }
    }

}