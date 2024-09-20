package com.example.waterremainderapp.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waterremainderapp.viewmodel.LogViewModel
import com.example.waterremainderapp.R
import com.example.waterremainderapp.fragments.HistoryFragment
import com.example.waterremainderapp.fragments.HomeFragment
import com.example.waterremainderapp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var logViewModel: LogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val historyFragment = HistoryFragment()
        val settingsFragment = SettingsFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationIcons)
        logViewModel=ViewModelProvider(this, LogViewModel.LogsVmFactory(application)).get(
            LogViewModel::class.java)

        replaceFragment(homeFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> replaceFragment(homeFragment)
                R.id.bottom_history -> replaceFragment(historyFragment)
                R.id.bottom_settings -> replaceFragment(settingsFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}
