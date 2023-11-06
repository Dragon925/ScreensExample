package com.github.dragon925.screensexample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dragon925.screensexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}