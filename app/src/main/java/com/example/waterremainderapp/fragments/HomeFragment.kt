package com.example.waterremainderapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waterremainderapp.AdapterClass
import com.example.waterremainderapp.viewmodel.LogViewModel
import com.example.waterremainderapp.database.Logs
import com.example.waterremainderapp.R
import com.ss.profilepercentageview.ProfilePercentageView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var adapter: AdapterClass
    private lateinit var recyclerView: RecyclerView
    private lateinit var recordsArrayList: ArrayList<Logs>
    private val logsViewModel: LogViewModel by activityViewModels()
    private var sharedPreferences: SharedPreferences? = null
    private lateinit var dailyGoals: TextView
    private lateinit var percentageView: ProfilePercentageView
    private lateinit var percentScore: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        percentageView = view.findViewById(R.id.PercentageView)
        percentScore = view.findViewById(R.id.Score_tv)

        val addWaterBtn = view.findViewById<Button>(R.id.AddWaterBtn)
        addWaterBtn.setOnClickListener {
            showAddWaterDialog()
        }
        dailyGoals = view.findViewById(R.id.QuantityTv)
        sharedPreferences =
            requireContext().getSharedPreferences("WaterRemainder", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences?.getString("daily_goal_number", "")
        val savedUnits = sharedPreferences?.getString("daily_goal_units", "ml")
        val savedFrequency = sharedPreferences?.getString("remainder_frequency_number", "")

        dailyGoals.text = savedNumber + " " + savedUnits

        observeLogs()
        return view
    }

    @SuppressLint("DefaultLocale")
    fun observeLogs () {
        lifecycleScope.launch {
            logsViewModel.allLogs.collect {
                if(it.isNotEmpty()) {
                    recordsArrayList = it as ArrayList
                    adapter = AdapterClass(recordsArrayList)
                    recyclerView.adapter = adapter
                    var totalQuantity = 0
                    for (i in it) {
                        totalQuantity += i.quantity
                    }
                    Log.d("percentage", totalQuantity.toString())
                    if (dailyGoals.text.split(" ")[0].isNotEmpty()) {
                        val values =
                            (totalQuantity / dailyGoals.text.split(" ")[0].toDouble()) * 100
                        percentScore.text = values.toInt().toString()
                        percentageView.setValue(values.toInt())
                    }
                }

            }
        }
    }

    @SuppressLint("MissingInflatedId", "DefaultLocale")
    private fun showAddWaterDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflator = layoutInflater
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.layout_customdialog, null)


        val editTextQuantity = dialogView.findViewById<EditText>(R.id.enterQuantity)
        val saveBtn = dialogView.findViewById<Button>(R.id.save_btn)
        val cancelBtn = dialogView.findViewById<Button>(R.id.cancel_btn)


        val dialog = builder.setView(dialogView).create()


        saveBtn.setOnClickListener {
            val quantity = editTextQuantity.text.toString()

            if (quantity.isNotEmpty()) {
//                adapter.addLog(newLog)
                logsViewModel.addLog(quantity.toInt(), getCurrentTime())

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