package com.example.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var textResult: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textResult)

    }

    //"view is the actual button, the id of the button (which is clicked) will refer to it"
    fun onDigit(view: View){
        // append is a function for imply
        textResult?.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view:View){
        textResult?.text = ""
    }

    fun onDecimal(view:View){
        if(lastNumeric && !lastDot){
            textResult?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onArithmetic(view: View){

        textResult?.text?.let { //first check the input empty or not
            if(lastNumeric && !isOperatorAdded(it.toString())){ //"it" is from the char sequence of lambda
                textResult?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onResult(view: View){
        if(lastNumeric){
            var resultValue = textResult?.text.toString()
            var prefix = ""
            try{
                if(resultValue.startsWith("-")){
                    prefix = "-"
                    resultValue = resultValue.substring(1)
                }
                if(resultValue.contains("-")){
                    val splitValue = resultValue.split("-")
                    var one = splitValue[0] // first value
                    var two = splitValue[1] // second value

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textResult?.text = (one.toDouble() - two.toDouble()).toString()
                }else if(resultValue.contains("+")){
                    val splitValue = resultValue.split("+")
                    var one = splitValue[0] // first value
                    var two = splitValue[1] // second value

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textResult?.text = (one.toDouble() + two.toDouble()).toString()
                }else if(resultValue.contains("/")){
                    val splitValue = resultValue.split("/")
                    var one = splitValue[0] // first value
                    var two = splitValue[1] // second value

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textResult?.text = (one.toDouble() / two.toDouble()).toString()
                }else if(resultValue.contains("*")){
                    val splitValue = resultValue.split("*")
                    var one = splitValue[0] // first value
                    var two = splitValue[1] // second value

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    textResult?.text = (one.toDouble() * two.toDouble()).toString()
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }


}