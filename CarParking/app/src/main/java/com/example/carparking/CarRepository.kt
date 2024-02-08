package com.example.carparking


import android.content.Context


class CarRepository(context: Context) {

    private val db = DBHelper.getInstance(context)
    suspend fun insertCarDetails(carDetails: Car) {
        db?.insertCarDetails(carDetails)
    }

    suspend fun getCarDetailsBySlotNumber(): List<Car>? {
        return db?.getCarDetailsBySlotNumber()
    }

    fun readCarSlotNumber(): MutableList<Int>? {
        return db?.readCarSlotNumber()
    }

    suspend fun removeCarDetailsBySlotNumber(slotNumber: Int?) {
        db?.removeCarDetailsBySlotNumber(slotNumber)
    }
}