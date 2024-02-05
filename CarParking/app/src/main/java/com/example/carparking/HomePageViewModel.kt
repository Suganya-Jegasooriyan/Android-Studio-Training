package com.example.carparking

import androidx.lifecycle.ViewModel

class HomePageViewModel(private val dbHelper: DBHelper) : ViewModel() {

    private var carSlotNumberList = mutableListOf<Int>()
    fun addCarDetails(carDetails: Car) {
        val nax = getNextAvailableSlot()
        if (nax == -1) {
            carDetails.slotNumber = carSlotNumberList.size + 1
        } else {
            carDetails.slotNumber = nax
        }
        dbHelper.insertCarDetails(carDetails)
        sendCarDetailsToAdapter()
    }

    private fun getNextAvailableSlot(): Int {
        this.carSlotNumberList = dbHelper.readCarSlotNumber()
        carSlotNumberList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) return index + 1
        }
        return -1
    }

    fun sendCarDetailsToAdapter(): MutableList<Car> {
        return dbHelper.getCarDetailsBySlotNumber()
    }

    fun removeCar(car: Car) {
        val slotNumber = car.slotNumber
        dbHelper.removeCarDetails(slotNumber)
        sendCarDetailsToAdapter()
    }
}