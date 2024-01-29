package com.example.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var tvOutput: TextView
    private lateinit var btnClickMe: Button
    private lateinit var tvname: EditText
    private lateinit var tvAge: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvOutput = findViewById(R.id.tv_output)
        tvname = findViewById(R.id.et_id)
        tvAge = findViewById(R.id.et_name)
        btnClickMe = findViewById(R.id.btn_click_me)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.user.observe(this, Observer { user ->
            tvOutput.text = "User Info: ${user.name}, ${user.age}"
        })

        btnClickMe.setOnClickListener {
            val name = tvname.text.toString()
            val age = tvAge.text.toString().toIntOrNull() ?: 0
            viewModel.updateUser(name, age)
        }
    }
}