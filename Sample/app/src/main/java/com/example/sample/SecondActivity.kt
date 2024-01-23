package com.example.sample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val fab: View = findViewById(R.id.floating_action_button)
        val textView : TextView = findViewById(R.id.textView)
        val toastButton :Button = findViewById<Button>(R.id.toast)
        textView.text = "0"
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show()
        }
        toastButton.setOnClickListener {
            Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT).show()
        }

        val editText: EditText = findViewById(R.id.value)
        val text : TextView = findViewById(R.id.output)
        val click : Button = findViewById(R.id.button)
//        click.setOnClickListener {
//            val input: String = editText.text.toString()
//            text.text = input
//        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
               Log.i("Test", "beforeTextChanged : " + s)
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.i("Test", "onTextChanged : " + s)
                text.text = s.toString()
            }
            override fun afterTextChanged(s: Editable) {
                Log.i("Test", "afterTextChanged : " + s.toString())

            }
        })
    }
}