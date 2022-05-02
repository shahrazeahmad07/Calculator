package com.uet.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private var lastDigit = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastDigit = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastDot = false
        lastDigit = false
    }

    fun onDecimalPoint(view: View) {
        if (lastDigit && !lastDot) {
            tvInput.append(".")
            lastDigit = false
            lastDot = true
        }
    }
}