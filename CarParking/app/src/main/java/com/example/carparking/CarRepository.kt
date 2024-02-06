package com.example.carparking

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class CarRepository(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CAR_PARKING"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Car"
        private const val CAR_NUMBER = "carNumber"
        private const val MOBILE_NUMBER = "mobileNumber"
        private const val SLOT_NUMBER = "slotNumber"
        private const val CHECK_IN = "checkIn"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + CAR_NUMBER + " TEXT , " +
                MOBILE_NUMBER + " INTEGER," +
                SLOT_NUMBER + " INTEGER PRIMARY KEY," +
                CHECK_IN + " LONG" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("================================onUpgrade()==========================")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertCarDetails(carDetails: Car) {
        val dbCarDetails = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(CAR_NUMBER, carDetails.carNumber)
        contentValue.put(MOBILE_NUMBER, carDetails.mobileNumber)
        contentValue.put(SLOT_NUMBER, carDetails.slotNumber)
        contentValue.put(CHECK_IN, carDetails.checkIn)
        dbCarDetails.insert(TABLE_NAME, null, contentValue)
        dbCarDetails.close()
    }

    @SuppressLint("Recycle")
    fun getCarDetailsBySlotNumber(): MutableList<Car> {
        val carDetailList = mutableListOf<Car>()
        val dbCarDetails = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $SLOT_NUMBER"
        val result = dbCarDetails.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val carNo = result.getString(result.getColumnIndexOrThrow(CAR_NUMBER)).toString()
                val mobileNo =
                    result.getString(result.getColumnIndexOrThrow(MOBILE_NUMBER)).toString()
                val slotNo = result.getString(result.getColumnIndexOrThrow(SLOT_NUMBER)).toInt()
                val checkIn = result.getString(result.getColumnIndexOrThrow(CHECK_IN)).toLong()
                val carDetail = Car(carNo, mobileNo, slotNo, checkIn)
                carDetailList.add(carDetail)
            } while (result.moveToNext())
        }
        dbCarDetails.close()
        return carDetailList
    }

    fun removeCarDetailsBySlotNumber(slotNumber: Int?) {
        val dbCarDetails = this.writableDatabase
        dbCarDetails.delete(TABLE_NAME, "$SLOT_NUMBER =$slotNumber", null)
        dbCarDetails.close()
    }

    @SuppressLint("Recycle")
    fun readCarSlotNumber(): MutableList<Int> {
        val carSlotNumberList = mutableListOf<Int>()
        val dbCarDetails = this.readableDatabase
        val query = "SELECT $SLOT_NUMBER FROM $TABLE_NAME ORDER BY $SLOT_NUMBER ASC"
        val result = dbCarDetails.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val slotNumber = result.getString(result.getColumnIndexOrThrow(SLOT_NUMBER)).toInt()
                carSlotNumberList.add(slotNumber)
            } while (result.moveToNext())
        }
        dbCarDetails.close()
        return carSlotNumberList
    }
}