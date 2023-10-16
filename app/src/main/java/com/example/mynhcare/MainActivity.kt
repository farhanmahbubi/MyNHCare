package com.example.mynhcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeTextView = findViewById<TextView>(R.id.textWelcome)
        welcomeTextView.text = "Welcome To NHCare!!!"
    }
}
