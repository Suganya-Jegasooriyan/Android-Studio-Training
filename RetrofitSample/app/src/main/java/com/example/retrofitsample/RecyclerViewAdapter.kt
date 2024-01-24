package com.example.retrofitsample


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private var body: List<MyDataItem>?, private val listener : RecyclerViewAdapter.OnClick) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

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
        return body!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel: MyDataItem = body!![position]
        holder.tvId.text = userModel.id.toString()
        holder.tvUserId.text = userModel.userId.toString()
        holder.tvTitle.text = userModel.title
        holder.tvBody.text = userModel.body
        holder.tvBody.setOnClickListener {
            listener.passData(holder.tvBody.text.toString(), holder.tvTitle.text.toString())
        }
    }

    interface  OnClick{
        fun passData(data:String, tit:String)
    }
}
