package com.example.testcoordinateapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import com.example.testcoordinateapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()

        Log.d(TAG, "onCreate:")
        checkLocation()

        binding.button.setOnClickListener {
            checkLocation()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume:")
        checkLocation()
    }

    private fun checkLocation(){
        val location = IntArray(2)

        binding.target1.getLocationOnScreen(location)
        Log.d(TAG, "Target 1 location: ${location[0]}\t ${location[1]}")

        binding.target2.getLocationOnScreen(location)
        Log.d(TAG, "Target 2 location: ${location[0]}\t ${location[1]}")
    }

    private fun initObserver(){
        val viewObserver: ViewTreeObserver = binding.target1.viewTreeObserver
        viewObserver.addOnGlobalLayoutListener {
            Log.d(TAG, "globalLayoutListener:")
            checkLocation()
        }
    }

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

}