package com.example.lbc_albums.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lbc_albums.R
import com.example.lbc_albums.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val FRAGMENT_TAG = "mainFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use Databinding to bind the view
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SavedInstanceState always null on activity's first load, prevent to create fragment when screen orientation changes
        val fragment: Fragment = if (savedInstanceState == null) {
            MainFragment()
        } else {
            supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)!!
        }

        // Create MainFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, fragment, FRAGMENT_TAG)
            .commit()
    }
}