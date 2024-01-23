package com.example.employeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val employeeList = mutableListOf<Employee>()
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLaunch.launch(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        employeeAdapter = EmployeeAdapter()
        recyclerView.adapter = employeeAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
    val resultLaunch = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data : Intent? = result.data
            val name = data?.getStringExtra("name")?:""
            val mobileNUmber  = data?.getStringExtra("mobileNumber")?:""
            val email = data?.getStringExtra("email")?:""
            val address = data?.getStringExtra("address")?:""
            val pincode = data?.getStringExtra("pincode")?:""
            val employee = Employee(name,mobileNUmber,email,address,pincode)
            employeeList.add(employee)
            employeeAdapter.submitData(employeeList)
        }
    }
}