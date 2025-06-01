package com.example.transactionviewer.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.transactionviewer.R
import com.example.transactionviewer.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    // data binding
 lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layout bind
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set timer for 3 seconds (3000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Go to MainActivity after delay
            startActivity(Intent(this, HomeAcitvity::class.java))
            finish() // Close splash screen
        }, 2000)

    }
}