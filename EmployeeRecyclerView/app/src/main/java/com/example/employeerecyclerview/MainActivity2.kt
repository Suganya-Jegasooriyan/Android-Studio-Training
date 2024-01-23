package com.example.employeerecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    private lateinit var nameText: EditText
    private lateinit var mobileNumberText: EditText
    private lateinit var emailText: EditText
    private lateinit var addressText: EditText
    private lateinit var pincodeText: EditText
    private var isFieldChecked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initialize()
    }

    private fun initialize() {
        val proceedButton : Button = findViewById(R.id.Proceed_Button)
        proceedButton.setOnClickListener {
            nameText = findViewById(R.id.name)
            mobileNumberText = findViewById(R.id.mobile_number)
            emailText = findViewById(R.id.email)
            addressText = findViewById(R.id.address)
            pincodeText = findViewById(R.id.pincode)
            isFieldChecked  = validateInputData(nameText.text.toString(),mobileNumberText.text.toString(),emailText.text.toString(),addressText.text.toString(),pincodeText.text.toString())
            if (isFieldChecked) {
                val employee = Employee(
                    name = nameText.text.toString(),
                    mobileNumber = mobileNumberText.text.toString(),
                    email = emailText.text.toString(),
                    address = addressText.text.toString(),
                    pincode = pincodeText.text.toString()
                )
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("data", employee)
//                startActivity(intent)
            }
        }
    }

    private fun validateInputData(
        name: String,
        mobileNumber: String,
        email: String, address: String, pincode: String): Boolean {

        if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (mobileNumber.isEmpty()) {
            Toast.makeText(this, "Mobile Number is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (address.isEmpty()) {
            Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pincode.isEmpty()) {
            Toast.makeText(this, "Pincode is required", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}