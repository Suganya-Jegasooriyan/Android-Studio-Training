package com.example.employeerecyclerview

import java.io.Serializable

data class Employee(
    val name: String,
    val mobileNumber: String,
    val email: String,
    val address: String,
    val pincode: String
) : Serializable