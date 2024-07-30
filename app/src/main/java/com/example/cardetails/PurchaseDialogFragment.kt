package com.example.cardetails

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class PurchaseDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val carName = arguments?.getString("CAR_NAME") ?: "Unknown Car"

        return AlertDialog.Builder(requireContext())
            .setTitle("Purchase Confirmation")
            .setMessage("You have purchased the $carName")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    companion object {
        fun newInstance(carName: String): PurchaseDialogFragment {
            val args = Bundle()
            args.putString("CAR_NAME", carName)
            val fragment = PurchaseDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
