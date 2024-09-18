package com.example.waterremainderapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.whenCreated
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val historyFragment = HistoryFragment()
        val settingsFragment = SettingsFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigationIcons)

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
