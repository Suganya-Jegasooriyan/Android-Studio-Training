package com.example.carparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CarDetailsActivity : AppCompatActivity() {

    private lateinit var tilCarNumber: TextInputLayout
    private lateinit var etCarNumber: TextInputEditText
    private lateinit var tilMobileNumber: TextInputLayout
    private lateinit var etMobileNumber: TextInputEditText
    private lateinit var btnCheckIn: Button
    private var isAllFieldsChecked: Boolean = false
    private lateinit var carViewModel: CarDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)
        carViewModel = ViewModelProvider(this)[CarDetailsViewModel::class.java]
        initView()
        addCarDetails()
    }

    private fun initView() {
        val toolbar: Toolbar = findViewById(R.id.carDetail_TitleBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tilCarNumber = findViewById(R.id.tilCarNumber)
        etCarNumber = findViewById(R.id.etCarNumber)
        tilMobileNumber = findViewById(R.id.tilMobileNumber)
        etMobileNumber = findViewById(R.id.etMobileNumber)
        btnCheckIn = findViewById(R.id.Check_In_Button)
    }

    private fun addCarDetails() {
        btnCheckIn.setOnClickListener {
            val carNumber = etCarNumber.text.toString()
            val mobileNumber = etMobileNumber.text.toString()
            val checkInTime = System.currentTimeMillis()
            val slotNumber = 0
            isAllFieldsChecked = checkAllFields(carNumber, mobileNumber)
            if (isAllFieldsChecked) {
                val carDetails = Car(carNumber, mobileNumber, slotNumber, checkInTime)
                carViewModel.insertCarDetails(carDetails)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun checkAllFields(carNumber: String, mobileNumber: String): Boolean {

        if (carNumber.isEmpty()) {
            tilCarNumber.error = Constants.carNumberRequired
            return false
        }
        if (mobileNumber.isEmpty()) {
            tilMobileNumber.error = Constants.mobileNumberRequired
            return false
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}