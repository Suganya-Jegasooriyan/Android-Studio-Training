package com.example.mvvmrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserActivity : AppCompatActivity(), Communicator {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapterForList()
    }

    private fun setAdapterForList() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val userListAdapter = UserListAdapter(this@UserActivity)
        val userViewModel: UserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        getUserItemData(userListAdapter, userViewModel)
    }

    private fun getUserItemData(userListAdapter: UserListAdapter, userViewModel: UserViewModel) {
        userViewModel.getLiveDataObserver().observe(this, Observer {
            if (it.isNotEmpty()) {
                recyclerView.adapter = userListAdapter
                userListAdapter.setUserItemList(it)
            } else {
                Toast.makeText(this, "Error in getting List", Toast.LENGTH_SHORT).show()
            }
        })
        userViewModel.makeAPICall()
    }

    override fun passData(title: String, body: String) {
        val intent = Intent(this, DisplayDetailsActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("body", body)
        startActivity(intent)
    }
}