package com.example.employeeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity2 : AppCompatActivity() {
    private lateinit var nameText: TextInputEditText
    private lateinit var nameLayout: TextInputLayout
    private lateinit var mobileNumberLayout: TextInputLayout
    private lateinit var mobileNumberText: TextInputEditText
    private lateinit var emailText: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var addressText: TextInputEditText
    private lateinit var addressLayout: TextInputLayout
    private lateinit var pincodeText: TextInputEditText
    private lateinit var pincodeLayout: TextInputLayout
    private lateinit var submitBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initView()
    }

    private fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        nameText = findViewById(R.id.etName)
        nameLayout = findViewById(R.id.tilName)
        mobileNumberText = findViewById(R.id.etMobileNumber)
        mobileNumberLayout = findViewById(R.id.tilMobileNumber)
        emailText = findViewById(R.id.etMail)
        emailLayout = findViewById(R.id.tilMail)
        addressText = findViewById(R.id.etAddress)
        addressLayout = findViewById(R.id.tilAddress)
        pincodeText = findViewById(R.id.etPincode)
        pincodeLayout = findViewById(R.id.tilPincode)
        submitBtn = findViewById(R.id.Submit_Button)


        fun validateName(): Boolean {
            val name: String = nameText.editableText.toString()
            return if (name.isEmpty()) {
                nameLayout.error = "Name Required"
                false
            } else true
        }

        fun validateMobileNumber(): Boolean {
            val mobileNumber: String = mobileNumberText.editableText.toString()
            return if (mobileNumber.isEmpty()) {
                mobileNumberLayout.error = "Mobile Number required"
                false
            } else true
        }

        fun validateEmail(): Boolean {
            val eMail: String = emailText.editableText.toString()
            return if (eMail.isEmpty()) {
                emailLayout.error = "Email required"
                false
            } else
                true
        }

        fun validateAddress(): Boolean {
            val address: String = addressText.editableText.toString()
            return if (address.isEmpty()) {
                addressLayout.error = "Address required"
                false
            } else true
        }

        fun validatePincode(): Boolean {
            val pincode: String = pincodeText.editableText.toString()
            return if (pincode.isEmpty()) {
                pincodeLayout.error = "Pincode required"
                false
            } else true
        }
        submitBtn.setOnClickListener {
            val name = nameText.text.toString()
            val mobileNumber = mobileNumberText.text.toString()
            val email = emailText.text.toString()
            val address = addressText.text.toString()
            val pincode = pincodeText.text.toString()
            val intent = Intent()
            if (validateName() && validateMobileNumber() && validateEmail() && validateAddress() && validatePincode()) {
                intent.putExtra("name", name)
                intent.putExtra("mobileNumber", mobileNumber)
                intent.putExtra("email", email)
                intent.putExtra("address", address)
                intent.putExtra("pincode", pincode)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please Enter correct details", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // step1 func validate : Boolean
    // step2 if(email.isEmpty && email valid regex) email edittext error

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}