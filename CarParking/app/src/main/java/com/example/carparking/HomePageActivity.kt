package com.example.carparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    private lateinit var carListAdapter: CarParkingAdapter
    private lateinit var carViewModel: HomePageViewModel
    private lateinit var rvCarList: RecyclerView
    private lateinit var rvInterfaceInstance : CarParkingInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        carViewModel = ViewModelProvider(this)[HomePageViewModel::class.java]
        initView()
        checkOutFragment()
        setAdapterForCarList()
    }

    private fun initView() {
        val checkInIc = findViewById<FloatingActionButton>(R.id.ic_check_in)
        checkInIc.setOnClickListener {
            val intent = Intent(this, CarDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkOutFragment() {
        rvInterfaceInstance = object : CarParkingInterface {
            override fun passData(car: Car) {
                val checkOutFragment = CheckOutDialogFragment()
                val bundle = Bundle()
                bundle.putParcelable(Constants.carDetails, car)
                checkOutFragment.arguments = bundle
                checkOutFragment.show(supportFragmentManager, Constants.test)
                carViewModel.removeCarDetails(car)
                getCarDetailsList()
            }
        }
    }
    private fun setAdapterForCarList() {
        rvCarList = findViewById(R.id.recycler_view)
        carListAdapter = CarParkingAdapter(rvInterfaceInstance)
        rvCarList.adapter = carListAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvCarList.layoutManager = layoutManager
        getCarDetailsList()
    }

    private fun getCarDetailsList() {
        carViewModel.setCarLiveData()
        carViewModel.getCarDetails().observe(this, Observer {
            carListAdapter.setCarList(it)
        })
    }

//    private val resultLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val data: Intent? = result.data
//            val carNumber = data?.getStringExtra(Constants.carNumber) ?: ""
//            val mobileNumber = data?.getStringExtra(Constants.mobileNumber) ?: ""
//            val checkInDateTime = System.currentTimeMillis()
//            val carDetails = Car(carNumber, mobileNumber, slotNumber = 0, checkInDateTime)
//            carViewModel.addCarDetails(carDetails)
//            carViewModel.setCarLiveData()
//            getCarDetailsList()
//        }
//    }
}

