package com.example.mvvmrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(mainActivity: MainActivity) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var dataList : List<UserItem>? = null

    fun setDataList(dataList : List<UserItem>?) {
        this.dataList = dataList
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tv_id)
        val tvUserId: TextView = view.findViewById(R.id.tv_user_id)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvBody: TextView = view.findViewById(R.id.tv_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel: UserItem = dataList!![position]
        holder.tvId.text = userModel.id.toString()
        holder.tvUserId.text = userModel.userId.toString()
        holder.tvTitle.text = userModel.title
        holder.tvBody.text = userModel.body
    }
}
