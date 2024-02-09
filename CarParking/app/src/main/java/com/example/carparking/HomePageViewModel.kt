package com.example.carparking

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    private val userItemLiveData = MutableLiveData<List<Car>>()
    private var carListRepository: CarRepository

    init {
        carListRepository = CarRepository(application)
    }

    fun setCarLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            userItemLiveData.postValue(carListRepository.getCarDetailsBySlotNumber())
        }
    }

    fun getCarDetails(): MutableLiveData<List<Car>> {
        return userItemLiveData
    }

    fun removeCarDetails(carDetails: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            val slotNumber = carDetails.slotNumber
            carListRepository.removeCarDetailsBySlotNumber(slotNumber)
            setCarLiveData()
        }
    }
}