package com.siddydevelops.flicker_kots

import android.view.View
import androidx.appcompat.app.AppCompatActivity

internal const val FLICKER_QUERY = "FLICKER_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

open class BaseActivity : AppCompatActivity() {
    internal fun activateToolbar(enableHome : Boolean) {
        val toolbar = findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}