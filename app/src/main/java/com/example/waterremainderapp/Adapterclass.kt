package com.example.waterremainderapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.http.CallbackException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
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
        holder.quantityTv.text = item.quantity.toString() + "" + "ml"
        holder.timeTv.text = item.time.toString()


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addLog(newData: Logs) {
        dataList.add(0, newData)
        notifyItemInserted(0)
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView
        val quantityTv: TextView
        val timeTv: TextView
        val threeDotsIV: ImageView

        init {
            imageView = ItemView.findViewById(R.id.DropletsIv)
            quantityTv = ItemView.findViewById(R.id.QuantityTv)
            timeTv = ItemView.findViewById(R.id.TimeTV)
            threeDotsIV = ItemView.findViewById(R.id.three_dotsIv)


            threeDotsIV.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(MenuItems: View) {
            val popupMenus = PopupMenu(MenuItems.context, MenuItems)
            popupMenus.inflate(R.menu.optionmenu_items)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editTv -> {
                        Toast.makeText(MenuItems.context, "editBtn is clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.delTv -> {
                        Toast.makeText(MenuItems.context, "deleteBtn is clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> true
                }
            }
            popupMenus.show()
        }
    }
}
