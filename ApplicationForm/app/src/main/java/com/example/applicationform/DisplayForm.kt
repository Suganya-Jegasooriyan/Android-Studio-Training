package com.example.applicationform

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_form)
        val nameText: TextView = findViewById(R.id.name_text)
        val mobileNumberText: TextView = findViewById(R.id.mobile_number_text)
        val emailText: TextView = findViewById(R.id.email_text)
        val addressText: TextView = findViewById(R.id.address_text)
        val pincodeText: TextView = findViewById(R.id.pincode_text)
        val bundle = intent.extras?.getBundle("data")
        val name = bundle?.getString("name")
        val mobileNumber = bundle?.getString("mobileNumber")
        val email = bundle?.getString("email")
        val address = bundle?.getString("address")
        val pincode = bundle?.getString("pincode")
        nameText.text = name
        mobileNumberText.text = mobileNumber
        emailText.text = email
        addressText.text = address
        pincodeText.text = pincode
    }
}