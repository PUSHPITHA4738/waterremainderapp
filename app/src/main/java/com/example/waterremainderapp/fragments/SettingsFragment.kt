package com.example.waterremainderapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waterremainderapp.R


class SettingsFragment: Fragment() {

    private lateinit var unitSpinner: Spinner
    private lateinit var hourSpinner: Spinner
    private lateinit var quantityEt: EditText
    private lateinit var saveButton: Button

    private val hoursList = listOf("1", "2", "3", "4", "5")
    private val unitsList = listOf("ml", "l")

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        saveButton = view.findViewById(R.id.setting_save_btn)
        quantityEt = view.findViewById(R.id.quantityEt)
        unitSpinner = view.findViewById(R.id.quantitySpinner)
        hourSpinner = view.findViewById(R.id.timeSpinner)
        quantityEt.inputType=InputType.TYPE_CLASS_NUMBER


        sharedPreferences = requireContext().getSharedPreferences("WaterRemainder", Context.MODE_PRIVATE)


        handleHoursSpinner()
        handleUnitsSpinner()
        getDataFromSharedPref()

        saveButton.setOnClickListener {
            val numberInput = quantityEt.text.toString()
            val selectUnit = unitSpinner.selectedItem.toString()
            val selectedNumber = hourSpinner.selectedItem.toString()


            with(sharedPreferences?.edit()) {
                this?.putString("daily_goal_number", numberInput)
                this?.putString("daily_goal_units", selectUnit)
                this?.putString("remainder_frequency_number", selectedNumber)
                this?.apply()
            }
            Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show()
        }


        return view
    }

    private fun handleUnitsSpinner() {
        val arrayAdapter1 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unitsList)
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = arrayAdapter1

    }

    private fun handleHoursSpinner() {
        val arrayAdapter2 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, hoursList)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        hourSpinner.adapter = arrayAdapter2
    }

    private fun getDataFromSharedPref() {
        val savedNumber = sharedPreferences?.getString("daily_goal_number", "")
        val savedUnits = sharedPreferences?.getString("daily_goal_units", "ml")
        val savedFrequency = sharedPreferences?.getString("remainder_frequency_number", "")



        quantityEt.setText(savedNumber)
        unitSpinner.setSelection(unitsList.indexOf(savedUnits))
        hourSpinner.setSelection(hoursList.indexOf(savedFrequency))

    }
}




