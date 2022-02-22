package com.tomiwo.quickqrscanner

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


class QRActivity : AppCompatActivity() {

    private lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)

        val etQrData : EditText = findViewById(R.id.et_qr_data)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val text: Spannable = SpannableString("Scan QR")
        text.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.app_blue)),
            0,
            text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE,
        )

        supportActionBar?.title = text

//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val imageQR : ImageView = findViewById(R.id.img_qr)
        imageQR.setOnClickListener {
            BottomSheetFragment().apply {

                val etQrData2 : EditText = findViewById(R.id.et_qr_data)
                val bundle = Bundle()
                bundle.putString("edttext", etQrData2.text.toString())
                this.arguments = bundle

                show(supportFragmentManager, tag)
            }
        }



        // Alternative to "onActivityResult", because that is "deprecated"
        mQrResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                    if (result.contents != null) {
                        // Do something with the contents (this is usually a URL)
                        var content = result.contents
                        println(result.contents)

                        etQrData.setText("${result.contents}")
                    }
                }
            }

        // Starts scanner on Create of Overlay (you can also call this function using a button click)
        startScanner()
    }

    // Start the QR Scanner
    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setBeepEnabled(false)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("Place scanner over the QR Code")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())


    }
}
