package com.example.carparking

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var carListRepository: CarRepository

    init {
        carListRepository = CarRepository(application)
    }
    fun insertCarDetails(carDetails: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            val nax = getNextAvailableSlot()
            carDetails.slotNumber = nax
            carListRepository.insertCarDetails(carDetails)
        }
    }

    private fun getNextAvailableSlot(): Int {
        val carSlotNumberList = carListRepository.readCarSlotNumber()!!
        carSlotNumberList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) return index + 1
        }
        return carSlotNumberList.size + 1
    }
}