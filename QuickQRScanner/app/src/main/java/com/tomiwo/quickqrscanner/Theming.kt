package com.tomiwo.quickqrscanner

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate

fun chooseThemeDialog(activity: MainActivity, delegate: AppCompatDelegate) {

    val builder = AlertDialog.Builder(activity)
    builder.setTitle(R.string.choose_theme_text)
    val styles = arrayOf("Light", "Dark", "System default")
    val checkedItem = UserPreference(activity).darkMode

    builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

        when (which) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                UserPreference(activity).darkMode = 0
                delegate.applyDayNight()
                dialog.dismiss()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                UserPreference(activity).darkMode = 1
                delegate.applyDayNight()
                dialog.dismiss()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                UserPreference(activity).darkMode = 2
                delegate.applyDayNight()
                dialog.dismiss()
            }

        }
    }

    val dialog = builder.create()
    dialog.show()
}