package com.tomiwo.quickqrscanner

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator


class QRActivity : AppCompatActivity() {

    private lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//
//// finally change the color
//        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)

        val text: Spannable = SpannableString("Scan QR")
        text.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.app_blue)),
            0,
            text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE,
        )

        supportActionBar?.title = text

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        // Alternative to "onActivityResult", because that is "deprecated"
        mQrResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                    if (result.contents != null) {
                        // Do something with the contents (this is usually a URL)
                        var content = result.contents
                        println(result.contents)

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
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("QR Code Scanner Prompt Text")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())


    }
}