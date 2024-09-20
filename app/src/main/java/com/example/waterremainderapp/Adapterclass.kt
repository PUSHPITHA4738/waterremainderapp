package com.example.waterremainderapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainderapp.database.Logs

class AdapterClass(private val dataList: ArrayList<Logs>) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClass.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterClass.ViewHolder, position: Int) {
        val item = dataList[position]
//        holder.imageView.setImageResource(ItemsViewModel.dataimage)
        holder.quantityTv.text = item.quantity.toString()
        holder.timeTv.text = item.time.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addLog(newData: Logs) {
        dataList.add(0, newData)
        notifyItemInserted(0)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = ItemView.findViewById(R.id.DropletsIv)
        val quantityTv: TextView = ItemView.findViewById(R.id.QuantityTv)
        val timeTv: TextView = ItemView.findViewById(R.id.TimeTV)

    }
}
