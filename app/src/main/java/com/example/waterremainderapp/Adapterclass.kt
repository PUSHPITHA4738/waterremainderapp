package com.example.waterremainderapp

import android.annotation.SuppressLint
import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AdapterClass(private val dataList:ArrayList<Dataclass>):RecyclerView.Adapter<AdapterClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClass.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterClass.ViewHolder, position: Int) {
        val ItemsViewModel=dataList[position]
        holder.imageView.setImageResource(ItemsViewModel.dataimage)
        holder.textview.text=ItemsViewModel.dataquantity
        holder.textView.text=ItemsViewModel.datatime
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

        @SuppressLint("NotifyDataSetChanged")
        fun addLog(newData: Dataclass) {
            dataList.add(0,newData)
            notifyItemInserted(0)
    }
    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        val imageView: ImageView=ItemView.findViewById(R.id.DropletsIv)
        val textview: TextView=ItemView.findViewById(R.id.QuantityTv)
        val textView: TextView=ItemView.findViewById(R.id.TimeTV)

    }
}
