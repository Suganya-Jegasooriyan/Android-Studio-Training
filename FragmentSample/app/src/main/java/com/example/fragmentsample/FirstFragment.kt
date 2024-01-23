package com.example.fragmentsample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.tabs.TabLayout.TabGravity

class FirstFragment() : Fragment(), Communicator {
    private var communicator: Communicator? = null
    private lateinit var etvInputValue: EditText
    private lateinit var btnOk: Button
    private lateinit var tvOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println("onCreate()-Fragment")
        }
    }
    companion object {
        const val TAG = "FirstFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView()-Fragment")
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvOutput = view.findViewById(R.id.tv_output)
        etvInputValue = view.findViewById<EditText>(R.id.tv_input)
        btnOk = view.findViewById<Button>(R.id.btn_ok)
        btnOk.setOnClickListener {
            communicator?.passData(etvInputValue.text.toString(), true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("onAttach()-Fragment")
        if (context is MainActivity) {
            communicator = context
        }
    }

    override fun passData(inputValue: String, boolean: Boolean) {
        tvOutput.text = inputValue
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("onActivityCreated()-Fragment")
    }

    override fun onStart() {
        super.onStart()
        println("onStart()-Fragment")
    }

    override fun onResume() {
        super.onResume()
        println("onResume()-Fragment")
    }

    override fun onPause() {
        super.onPause()
        println("onPause()-Fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy()-Fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        communicator = null
        println("onDestroyView()-Fragment")
    }

    override fun onDetach() {
        super.onDetach()
        println("onDetach()-Fragment")
    }

}