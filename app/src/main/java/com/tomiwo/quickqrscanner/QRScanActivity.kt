package com.tomiwo.quickqrscanner

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView


class QRScanActivity : AppCompatActivity() {

//private lateinit var toolbar : Toolbar
//private lateinit var tvToolbarTitle : TextView
//private lateinit var imgGallery : ImageView



    private val rotateOpenAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation)}
    private val rotateCloseAnimation: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation)}
    private val fromBottomAnimation: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation)}
    private val toBottomAnimation: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscan)

//        toolbar = findViewById(R.id.toolbar_scan)
//        tvToolbarTitle = findViewById(R.id.tv_toolbar_title)
//        imgGallery = findViewById(R.id.img_gallery)
//
//        toolbar.setBackgroundColor(resources.getColor(R.color.white))
//
//        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_left_48)
//        toolbar.navigationIcon?.setTint(resources.getColor(R.color.app_blue))
//
//        tvToolbarTitle.text = "Scan QR"
//        tvToolbarTitle.textSize = 20.0F
//        tvToolbarTitle.setTextColor(resources.getColor(R.color.app_blue))


//        imgGallery.background = resources.getDrawable(R.drawable.gallery_24)
//        imgGallery.maxHeight = 40

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_white))
        supportActionBar?.title = "Scan QR"


//        val text: Spannable = SpannableString("Scan QR")
//        text.setSpan(
//            ForegroundColorSpan(Color.BLUE),
//            0,
//            text.length,
//            Spannable.SPAN_INCLUSIVE_INCLUSIVE
//        )
//        supportActionBar?.title = text
    }
}