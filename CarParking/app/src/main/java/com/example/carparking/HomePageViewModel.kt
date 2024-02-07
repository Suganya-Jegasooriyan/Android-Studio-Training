package com.example.carparking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    private val userItemLiveData = MutableLiveData<List<Car>>()
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
        setCarLiveData()
    }

    fun setCarLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            userItemLiveData.postValue(carListRepository.getCarDetailsBySlotNumber())
        }

    }

    private fun getNextAvailableSlot(): Int {
        carSlotNumberList = carListRepository.readCarSlotNumber()
        carSlotNumberList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) return index + 1
        }
        return -1
    }

    fun getCarDetails(): MutableLiveData<List<Car>> {
        return userItemLiveData
    }

    fun removeCarDetails(carDetails: Car) {

        val slotNumber = carDetails.slotNumber
        carListRepository.removeCarDetailsBySlotNumber(slotNumber)
        setCarLiveData()
    }
}