package com.srpallab.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var buttonOne: Button? = null
    private var buttonTwo: Button? = null
    private var buttonThree: Button? = null
    private var buttonFour: Button? = null
    private var buttonFive: Button? = null
    private var buttonSix: Button? = null
    private var buttonSeven: Button? = null
    private var buttonEight: Button? = null
    private var buttonNine: Button? = null
    private var buttonZero: Button? = null
    private var buttonDivide: Button? = null
    private var buttonMultiply: Button? = null
    private var buttonAdd: Button? = null
    private var buttonMinus: Button? = null
    private var buttonEqual: Button? = null
    private var buttonClear: Button? = null
    private var tvInputText: TextView? = null
    private var buttonDot: Button? = null
    private var isLastClickDot: Boolean = false
    private var isLastClickDigit: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOne = findViewById(R.id.buttonOne)
        buttonTwo = findViewById(R.id.buttonTwo)
        buttonThree = findViewById(R.id.buttonThree)
        buttonFour = findViewById(R.id.buttonFour)
        buttonFive = findViewById(R.id.buttonFive)
        buttonSix = findViewById(R.id.buttonSix)
        buttonSeven = findViewById(R.id.buttonSeven)
        buttonEight = findViewById(R.id.buttonEight)
        buttonNine = findViewById(R.id.buttonNine)
        buttonZero = findViewById(R.id.buttonZero)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonMultiply = findViewById(R.id.buttonMultiplication)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonEqual = findViewById(R.id.buttonEquals)
        buttonClear = findViewById(R.id.buttonClr)
        tvInputText = findViewById(R.id.tvInputText)
        buttonDot = findViewById(R.id.buttonDot)

        val allButtons: List<Button?> = listOf(
            buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour,
            buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine
        )
        val allOperatorButtons: List<Button?> = listOf(
            buttonMinus, buttonAdd, buttonMultiply, buttonDivide
        )

        for (i in 0..9) {
            allButtons[i]?.setOnClickListener {
                tvInputText?.append(i.toString())
                isLastClickDigit = true
                isLastClickDot = false
            }
        }

        buttonClear?.setOnClickListener {
            tvInputText?.text = ""
            isLastClickDigit = false
            isLastClickDot = false
        }

        buttonDot?.setOnClickListener {
            if (isLastClickDigit && !isLastClickDot) {
                tvInputText?.append(".")
                isLastClickDot = true
                isLastClickDigit = false
            }
        }

        allOperatorButtons.forEach { button ->
            tvInputText?.text?.let { char ->
                button?.setOnClickListener {
                    if (isLastClickDigit && !isOperatorAdded(char.toString())) {
                        tvInputText?.append(button.text)
                        isLastClickDigit = false
                        isLastClickDot = false
                    } else if (button.text == "-") {
                        tvInputText?.append(button.text)
                    }
                }
            }
        }

        buttonEqual?.setOnClickListener {
            if (isLastClickDigit) {
                var tvValue : String = tvInputText?.text.toString()
                var prefix = ""
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var firstNumber = splitValue[0]
                    val lastNumber = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstNumber = prefix + firstNumber
                    }
                    tvInputText?.text = (firstNumber.toDouble() - lastNumber.toDouble()).toString()
                } else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var firstNumber = splitValue[0]
                    val lastNumber = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstNumber = prefix + firstNumber
                    }
                    tvInputText?.text = (firstNumber.toDouble() + lastNumber.toDouble()).toString()
                } else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var firstNumber = splitValue[0]
                    val lastNumber = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstNumber = prefix + firstNumber
                    }
                    tvInputText?.text = (firstNumber.toDouble() * lastNumber.toDouble()).toString()
                } else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var firstNumber = splitValue[0]
                    val lastNumber = splitValue[1]

                    if (prefix.isNotEmpty()){
                        firstNumber = prefix + firstNumber
                    }
                    tvInputText?.text = (firstNumber.toDouble() / lastNumber.toDouble()).toString()
                }

            }
        }

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }
}