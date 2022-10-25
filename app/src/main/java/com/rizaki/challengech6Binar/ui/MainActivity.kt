package com.rizaki.challengech6Binar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizaki.challengech6Binar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}