package com.example.mvvmrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserListAdapter(private val communicator: Communicator) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private var userItemList: List<UserItem>? = arrayListOf()

    fun setUserItemList(userItemList: List<UserItem>?) {
        this.userItemList = userItemList
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
        return userItemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItemModel: UserItem = userItemList!![position]
        holder.tvId.text = userItemModel.id.toString()
        holder.tvUserId.text = userItemModel.userId.toString()
        holder.tvTitle.text = userItemModel.title
        holder.tvBody.text = userItemModel.body
        holder.tvBody.setOnClickListener {
            communicator.passData(holder.tvTitle.text.toString(), holder.tvBody.text.toString())
        }
    }
}
