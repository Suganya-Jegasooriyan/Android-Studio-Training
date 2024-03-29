package com.example.carparking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class CarParkingAdapter(
    private val carParkingInterface: CarParkingInterface
) : RecyclerView.Adapter<CarParkingAdapter.ViewHolder>() {
    private var carDetailLists: List<Car> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCarNumber: TextView = view.findViewById(R.id.show_car_number)
        val tvMobileNumber: TextView = view.findViewById(R.id.show_mobile_number)
        val tvSlotNumber: TextView = view.findViewById(R.id.show_slot_number)
        val tvCheckInDateTime: TextView = view.findViewById(R.id.show_check_in)
        val btnCheckOut: Button = view.findViewById(R.id.check_out_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_display, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carDetailLists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carDetails: Car = carDetailLists[position]
        holder.tvCarNumber.text = carDetails.carNumber
        holder.tvMobileNumber.text = carDetails.mobileNumber
        holder.tvSlotNumber.text = carDetails.slotNumber.toString()
        val date = changeSimpleDateFormat(carDetails.checkIn)
        holder.tvCheckInDateTime.text = date
        holder.btnCheckOut.setOnClickListener {
            carParkingInterface.passData(carDetails)
        }
    }

    private fun changeSimpleDateFormat(checkIn: Long?): String {
        val date = SimpleDateFormat(Constants.dateFormat, Locale.getDefault())
        return date.format(checkIn)
    }

    fun setCarList(carDetailList: List<Car>) {
        this.carDetailLists = carDetailList
        notifyDataSetChanged()
    }
}
