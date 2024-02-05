package com.example.carparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    private val dbHandler = DBHelper(this)

    private lateinit var carParkingAdapter: CarParkingAdapter
    private var carViewModel = HomePageViewModel(dbHandler)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        carViewModel = ViewModelProvider(this)[HomePageViewModel::class.java]
        initView()
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
                carViewModel.removeCar(car)
            }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        carParkingAdapter = CarParkingAdapter(rvInterfaceInstance)
        recyclerView.adapter = carParkingAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        carParkingAdapter.setCarList(carViewModel.sendCarDetailsToAdapter())
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
            carViewModel.addCarDetails(carDetails)
        }
    }
}

