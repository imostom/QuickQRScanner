package com.tomiwo.quickqrscanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet_details_share, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val strtext = arguments?.getString("edttext")
        val imgShare: ImageView? = view?.findViewById(R.id.img_share)

        if(strtext == null || strtext == "") {
            imgShare?.isEnabled = false
            Toast.makeText(view?.context, "Empty result can't be shared. Please Scan a QR Code", Toast.LENGTH_SHORT).show()
        }
        else {
            val etQRContent = view?.findViewById<EditText>(R.id.et_qr_content)
            etQRContent!!.setText(strtext)
            imgShare?.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, strtext)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

    }
}