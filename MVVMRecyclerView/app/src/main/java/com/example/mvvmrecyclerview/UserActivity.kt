package com.example.mvvmrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class UserActivity : AppCompatActivity(), Communicator {

    private lateinit var rvUserList: RecyclerView
    private lateinit var userListViewModel: UserListViewModel
    private lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        setAdapterForList()
        getUserItemData()
    }

    private fun setAdapterForList() {
        rvUserList = findViewById(R.id.recyclerview)
        rvUserList.layoutManager = LinearLayoutManager(this)
        userListAdapter = UserListAdapter(this@UserActivity)
    }

    private fun getUserItemData() {
        userListViewModel.setUserListLiveData()
        userListViewModel.getPostListLiveData().observe(this, Observer {
            try {
                if (it.isNotEmpty()) {
                    rvUserList.adapter = userListAdapter
                    userListAdapter.setUserItemList(it)
                } else {
                    Toast.makeText(this, "Error in getting List", Toast.LENGTH_SHORT).show()
                }
            } catch ( e : Exception) {
                println(e.message)
            }
        })
    }

    override fun passData(id: String) {
        val intent = Intent(this, DisplayDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}