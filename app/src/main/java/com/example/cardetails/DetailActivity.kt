package com.example.cardetails

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var detailCarDetails: TextView
    private lateinit var detailPrice: TextView
    private lateinit var buyButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailCarDetails = findViewById(R.id.detailCarDetails)
        detailPrice = findViewById(R.id.detailPrice)
        buyButton = findViewById(R.id.buyButton)
        backButton = findViewById(R.id.backButton)

        // Retrieve and display car details and price
        val carDetails = intent.getStringExtra("CAR_DETAILS")
        val carColor = intent.getStringExtra("CAR_COLOR")
        val carName = carDetails?.split("\n")?.get(0)?.substringAfter("Model: ") ?: "Unknown Car"
        detailCarDetails.text = "$carDetails\nColor: $carColor"
        detailPrice.text = carDetails?.substringAfter("Price: ") ?: "Price not available"

        buyButton.setOnClickListener {
            // Show purchase dialog
            val dialog = PurchaseDialogFragment.newInstance(carName)
            dialog.show(supportFragmentManager, "PurchaseDialog")
        }

        backButton.setOnClickListener {
            // Go back to main page
            finish()
        }
    }
}
