package com.app.recyclerviewtracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.recyclerviewtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlphabet.setOnClickListener {
            startActivity(Intent(this, AlphabetActivity::class.java))
        }

        binding.btnThumb.setOnClickListener {
            startActivity(Intent(this, ThumbActivity::class.java))
        }
    }
}