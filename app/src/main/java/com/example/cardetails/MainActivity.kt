package com.example.cardetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var carSpinner: Spinner
    private lateinit var carDetails: TextView
    private lateinit var purchaseButton: Button
    private lateinit var colorGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carSpinner = findViewById(R.id.carSpinner)
        carDetails = findViewById(R.id.carDetails)
        purchaseButton = findViewById(R.id.purchaseButton)
        colorGroup = findViewById(R.id.colorGroup)

        // Setup car spinner with real car names and details
        val cars = arrayOf("Select a car", "Tesla Model S", "Ford Mustang", "Chevrolet Camaro")
        carSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cars)

        carSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Update car details based on selected car
                carDetails.text = when (position) {
                    1 -> "Model: Tesla Model S\nType: Electric\nPrice: $79,990"
                    2 -> "Model: Ford Mustang\nType: Gasoline\nPrice: $55,300"
                    3 -> "Model: Chevrolet Camaro\nType: Gasoline\nPrice: $25,000"
                    else -> ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when nothing is selected
                carDetails.text = ""
            }
        }

        purchaseButton.setOnClickListener {
            // Check if a car is selected
            if (carSpinner.selectedItemPosition == 0) {
                Toast.makeText(this, "Please select a car", Toast.LENGTH_SHORT).show()
            } else {
                // Navigate to DetailActivity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("CAR_DETAILS", carDetails.text.toString())
                intent.putExtra("CAR_COLOR", getSelectedColor())
                startActivity(intent)
            }
        }
    }

    private fun getSelectedColor(): String {
        return when (colorGroup.checkedRadioButtonId) {
            R.id.colorRed -> "Red"
            R.id.colorBlue -> "Blue"
            R.id.colorGreen -> "Green"
            else -> "Not Selected"
        }
    }
}
