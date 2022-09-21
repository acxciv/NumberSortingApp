package com.example.bubblesortapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textBox: TextView = findViewById(R.id.editTextTextPersonName)
        val sortBtn: Button = findViewById(R.id.button)
        sortBtn.isEnabled = false
        var displaySortedNums: TextView = findViewById(R.id.textView3)
        displaySortedNums.visibility= View.INVISIBLE
        textBox.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Fires right as the text is being changed (even supplies the range of text)
                sortBtn.isEnabled = false
                val inputList = textBox.text.toString()
                val stringInputList = inputList.split(",").toTypedArray()
                var numbersOnly = true
                // Make sure that input list has 8 items and numbers only
                if (stringInputList.size in 3..8 && stringInputList.last().isNotEmpty()) {
                    for (input in stringInputList) {
                        if (!input.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                            numbersOnly = false
                        }
                    }
                    sortBtn.isEnabled = numbersOnly
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Fires right before text is changing
            }

            override fun afterTextChanged(s: Editable) {
                // Fires right after the text has changed
            }
        })

        sortBtn.setOnClickListener() {
            val textVal: String = textBox.text.toString()

            val stringListOfNumbers = textVal.split(",").toTypedArray()
            if (!stringListOfNumbers.isNullOrEmpty())
            {
                // Convert each string element to an IntArray type
                val intListOfNumber = IntArray(stringListOfNumbers.size)
                stringListOfNumbers.indices.forEach() {intListOfNumber[it] = stringListOfNumbers[it].toInt()}
                // Call the bubble sort app
                bubbleSort(intListOfNumber)
                val txtd: String =textView3.text.toString()
                displaySortedNums.visibility=View.VISIBLE


            }
        }
        // Exit From The Application
        button2.setOnClickListener(){
            this.finishAffinity()
            exitProcess(0)
        }
    }

    private fun getNumbersAsString(): CharSequence {
        //Commas and spaces are allowed
        val stringCopy=findViewById<TextView>(R.id.editTextTextPersonName).text
        val stringFilter= stringCopy.replace(("[,\\s]").toRegex(), "")
        return stringCopy
    }
    //Only numbers are allowed, no character
    private fun isNumeric(input: String): Boolean =
        try {
            input.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }

    // Alert an Error Message to the user
    private fun showDialog(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Please Enter Numbers Only")
        builder.setPositiveButton("OK"){ dialog, whichButton ->
            editTextTextPersonName.text =null
            dialog.cancel()

        }
        builder.setNegativeButton("Cancel"){ dialog, whichButton ->
            dialog.cancel()
            editTextTextPersonName.text= null
            //this.finish()
        }
        builder.show()
    }


    private fun bubbleSort(arr: IntArray):IntArray{
        val textView: TextView = findViewById(R.id.textView4)
        textView.movementMethod = ScrollingMovementMethod()
        var arrayString = ""
        textView.text = ""
        var temptext: String
        var swap = true
        var step = 0
        while(swap){
            swap = false
            for(i in 0 until arr.size-1){
                if(arr[i] > arr[i + 1]){
                    val temp = arr[i]
                    arr[i] = arr[i + 1]
                    arr[i + 1] = temp

                    swap = true
                    for (element in arr) {
                        arrayString += "$element,"
                    }
                    textView.append("Step $step: " + arrayString.dropLast(1) + "\n")
                    arrayString = ""
                    step++
                }
            }
        }
        // Display the Sorted Array
        for (element in arr) {
            if(element == arr.last()){
            arrayString += "$element"
            }
            else{
                arrayString += "$element,"
            }
        }

         textView5.text= arrayString
        return arr
    }


    private fun printList(list: IntArray) {
        for (k in list) print("$k ")  // Need to print list in UI here
    }


}