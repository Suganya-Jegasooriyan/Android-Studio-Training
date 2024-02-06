package com.example.carparking

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    private var carListRepository: CarRepository
    private var carSlotNumberList = mutableListOf<Int>()

    init {
        carListRepository = CarRepository(application.applicationContext)
    }
    fun addCarDetails(carDetails: Car) {
        val nax = getNextAvailableSlot()
        if (nax == -1) {
            carDetails.slotNumber = carSlotNumberList.size + 1
        } else {
            carDetails.slotNumber = nax
        }
        carListRepository.insertCarDetails(carDetails)

    }

    private fun getNextAvailableSlot(): Int {
        this.carSlotNumberList = carListRepository.readCarSlotNumber()
        carSlotNumberList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) return index + 1
        }
        return -1
    }

    fun getCarDetails(): MutableList<Car> {
        return carListRepository.getCarDetailsBySlotNumber()
    }

    fun removeCarDetails(carDetails: Car) {
        val slotNumber = carDetails.slotNumber
        carListRepository.removeCarDetailsBySlotNumber(slotNumber)
    }
}