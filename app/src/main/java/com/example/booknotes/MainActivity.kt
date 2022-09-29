package com.example.booknotes

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.booknotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        val navController = findNavController(R.id.fragmentContainerView)

        binding.bottomNavigationView.setupWithNavController(navController)

        /*var currentId = navController.currentDestination?.id

        if(currentId == R.id.booksFragment){
            binding.bottomNavigationView.visibility = View.VISIBLE
        } else if(currentId == R.id.settingsFragment) {
            binding.bottomNavigationView.visibility = View.VISIBLE
        } else {
            binding.bottomNavigationView.visibility = View.GONE
        }*/
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.booksFragment -> binding.bottomAppBar.visibility = View.VISIBLE
                R.id.settingsFragment -> binding.bottomAppBar.visibility = View.VISIBLE
                else -> binding.bottomAppBar.visibility = View.GONE
            }
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}