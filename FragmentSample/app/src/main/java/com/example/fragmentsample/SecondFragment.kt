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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment() : Fragment(), Communicator {

    private var communicator : Communicator? = null
    private lateinit var tvOutputValue: TextView
    private lateinit var etvInput : EditText
    private lateinit var btnOk : Button

    companion object {
        const val TAG = "SecondFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        tvOutputValue = view.findViewById(R.id.tv_output)
        etvInput = view.findViewById(R.id.tv_input_second_fragment)
        btnOk = view.findViewById(R.id.btn_ok_second_fragment)
        btnOk.setOnClickListener {
            communicator?.passData(etvInput.text.toString(),false)
        }
        return view
    }

    override fun passData(inputValue: String, boolean: Boolean) {
        tvOutputValue.text = inputValue
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) {
            communicator = context
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        communicator = null
    }
}