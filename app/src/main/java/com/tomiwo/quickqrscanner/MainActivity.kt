package com.tomiwo.quickqrscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private var tvHello : TextView? = null
    private var btnGetStarted : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGetStarted)
        btnGetStarted?.setOnClickListener {
            val intent = Intent(this, QRActivity::class.java)
            startActivity(intent)
        }

//        tvHello = findViewById(R.id.tv_Hello)
//        tvHello?.setOnClickListener {
//            chooseThemeDialog(this, delegate)
//        }
    }


    fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.choose_theme_text)
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = UserPreference(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    UserPreference(this).darkMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    UserPreference(this).darkMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    UserPreference(this).darkMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }
}