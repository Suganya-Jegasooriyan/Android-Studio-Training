package com.example.carparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    private val dbHandler = DBHelper(this)
    private var carSlotNumberList = mutableListOf<Int>()
    private lateinit var carParkingAdapter: CarParkingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        initView()
    }

    private fun addCarDetails(carDetails: Car) {
        val nax = getNextAvailableSlot()
        if (nax == -1) {
            carDetails.slotNumber = carSlotNumberList.size + 1
        } else {
            carDetails.slotNumber = nax
        }
        dbHandler.insertCarDetails(carDetails)
        sendCarDetailsToAdapter()
    }

    private fun sendCarDetailsToAdapter() {
        val list: MutableList<Car> = dbHandler.getCarDetailsBySlotNumber()
        carParkingAdapter.setCarList(list)
    }

    private fun getNextAvailableSlot(): Int {
        this.carSlotNumberList = dbHandler.readCarSlotNumber()
        carSlotNumberList.forEachIndexed { index, slotNumber ->
            if (slotNumber != index + 1) return index + 1
        }
        return -1
    }

    fun removeCar(car: Car) {
        val slotNumber = car.slotNumber
        dbHandler.removeCarDetails(slotNumber)
        sendCarDetailsToAdapter()
    }

    private fun initView() {
        val checkInIc = findViewById<FloatingActionButton>(R.id.ic_check_in)
        checkInIc.setOnClickListener {
            val intent = Intent(this, CarDetailsActivity::class.java)
            resultLauncher.launch(intent)
        }

        val rvInterfaceInstance: CarParkingInterface = object : CarParkingInterface {
            override fun onClick(car: Car) {
                val checkOutFragment = CheckOutDialogFragment()
                val bundle = Bundle()
                bundle.putParcelable(Constants.carDetails, car)
                checkOutFragment.arguments = bundle
                checkOutFragment.show(supportFragmentManager, Constants.test)
                removeCar(car)
            }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        carParkingAdapter = CarParkingAdapter(rvInterfaceInstance)
        recyclerView.adapter = carParkingAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val carNumber = data?.getStringExtra(Constants.carNumber) ?: ""
            val mobileNumber = data?.getStringExtra(Constants.mobileNumber) ?: ""
            val checkInDateTime = System.currentTimeMillis()
            val carDetails = Car(carNumber, mobileNumber, slotNumber = 0, checkInDateTime)
            addCarDetails(carDetails)
        }
    }
}

