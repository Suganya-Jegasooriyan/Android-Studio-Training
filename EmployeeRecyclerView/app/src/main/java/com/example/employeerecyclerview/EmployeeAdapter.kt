package com.example.employeerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val employeeList: MutableList<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.showName)
        val tvMobileNumber: TextView = view.findViewById(R.id.showMobileNumber)
        val tvEmail: TextView = view.findViewById(R.id.showEmail)
        val tvAddress: TextView = view.findViewById(R.id.showAddress)
        val tvPinCode: TextView = view.findViewById(R.id.showPincode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employeeModel: Employee = employeeList[position]
        holder.tvName.text = employeeModel.name
        holder.tvMobileNumber.text = employeeModel.mobileNumber
        holder.tvEmail.text = employeeModel.email
        holder.tvAddress.text = employeeModel.address
        holder.tvPinCode.text = employeeModel.pincode
    }
}