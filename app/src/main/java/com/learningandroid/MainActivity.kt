package com.learningandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learningandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}