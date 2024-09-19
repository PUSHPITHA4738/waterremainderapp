package com.example.waterremainderapp

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.profilepercentageview.ProfilePercentageView
import java.text.SimpleDateFormat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import java.time.temporal.TemporalQueries
import java.util.Locale




class HomeFragment : Fragment() {
    private lateinit var adapter: AdapterClass
    private lateinit var recyclerView: RecyclerView
    private lateinit var recordsArrayList: ArrayList<Dataclass>
    private val  logsViewModel:LogViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home,container, false)

        recyclerView = view.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recordsArrayList = ArrayList()
        adapter = AdapterClass(recordsArrayList)
        recyclerView.adapter = adapter


        val addWaterBtn = view.findViewById<Button>(R.id.AddWaterBtn)
        addWaterBtn.setOnClickListener {
            showAddWaterDialog()
        }
        return view
    }


    @SuppressLint("MissingInflatedId")
    private fun showAddWaterDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflator = layoutInflater
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_customdialog, null)


        val waterImageView = dialogView.findViewById<ImageView>(R.id.DropletsIv)
        val editTextQuantity = dialogView.findViewById<EditText>(R.id.enterQuantity)
        val saveBtn = dialogView.findViewById<Button>(R.id.save_btn)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancel_btn)


        val dialog = builder.setView(dialogView).create()


        saveBtn.setOnClickListener {
            val quantity = editTextQuantity.text.toString()
            if (quantity.isNotEmpty()) {
                val newLog = Dataclass(
                    R.drawable.waterdrop,
                    "$quantity ml",
                    getCurrentTime()
                )
                adapter.addLog(newLog)
                Toast.makeText(requireContext(), "Log added", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "please enter quantity", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(currentTime).toLowerCase(Locale.getDefault())
    }
}
