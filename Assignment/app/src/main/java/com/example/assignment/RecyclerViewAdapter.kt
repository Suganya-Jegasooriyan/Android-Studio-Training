package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.Test>() {


    class Test(itemView: View) : ViewHolder(itemView) {
        val test : TextView = itemView.findViewById(R.id.tvTest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Test {
       return Test(
           LayoutInflater.from(parent.context).inflate(R.layout.row_assignment_detail, parent, false)
       )
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: Test, position: Int) {
        holder.test.text = position.toString()
    }
}