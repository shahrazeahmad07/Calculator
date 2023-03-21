package com.uet.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private var lastDigit = true
    private var lastDot = false
    private val decimalFormat: DecimalFormat = DecimalFormat("0.0000")

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
    }

    fun onDecimalPoint(view: View) {
        if (lastDigit && !lastDot) {
            tvInput.append(".")
            lastDigit = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastDigit && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastDigit = false
            lastDot = false

        }
    }

    fun onEqual(view: View) {
        if(lastDigit) {
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    tvValue = tvValue.substring(1)
                    prefix = "-"
                }
                //! Subtract Code.
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZero(decimalFormat.format((one.toDouble() - two.toDouble())).toString())
                }

                //! Addition code
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZero(decimalFormat.format((one.toDouble() + two.toDouble())).toString())
                }

                //! Multiplication
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput.text = removeZero(decimalFormat.format((one.toDouble() * two.toDouble())).toString())
                }

                //! Division
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (two != "0") {
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput.text = removeZero(decimalFormat.format((one.toDouble() / two.toDouble())).toString())
                    } else {
                        tvInput.text = getString(R.string.infinity)
                    }
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZero(result: String): String {

        return if (result.contains(".0")) {
            val resultWithoutZero = result.substring(0, result.length-2)
            resultWithoutZero
        } else
            result
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")) {
            false
        }
        else {
            value.contains("+") || value.contains("-") || value.contains("/") || value.contains("*")
        }
    }
}