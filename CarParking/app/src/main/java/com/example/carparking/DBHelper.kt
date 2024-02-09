package com.example.carparking

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper private constructor(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        fun getInstance(context: Context) : DBHelper? {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = DBHelper(context)
                    }
                }
            }
            return INSTANCE
        }
        private const val DATABASE_NAME = "CAR_PARKING"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Car"
        private const val CAR_NUMBER = "carNumber"
        private const val MOBILE_NUMBER = "mobileNumber"
        private const val SLOT_NUMBER = "slotNumber"
        private const val CHECK_IN = "checkIn"
        private const val ID ="id"
        private var INSTANCE : DBHelper? = null
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + "INTEGER PRIMARY KEY,"
                + CAR_NUMBER + " TEXT , " +
                MOBILE_NUMBER + " INTEGER," +
                SLOT_NUMBER + " INTEGER," +
                CHECK_IN + " LONG" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    suspend fun insertCarDetails(carDetails: Car) {
        val dbCarDetails = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(CAR_NUMBER, carDetails.carNumber)
        contentValue.put(MOBILE_NUMBER, carDetails.mobileNumber)
        contentValue.put(SLOT_NUMBER, carDetails.slotNumber)
        contentValue.put(CHECK_IN, carDetails.checkIn)
        dbCarDetails.insert(TABLE_NAME, null, contentValue)
    }

    suspend fun getCarDetailsBySlotNumber(): MutableList<Car> {
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
        return carDetailList
    }

    suspend fun removeCarDetailsBySlotNumber(slotNumber: Int?) {
        val dbCarDetails = this.writableDatabase
        dbCarDetails.delete(TABLE_NAME, "$SLOT_NUMBER =$slotNumber", null)
    }

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
        return carSlotNumberList
    }

}