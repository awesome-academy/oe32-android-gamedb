package com.project.gamedb.ultis

import android.content.Context
import android.widget.Toast

fun Context.showToast(data: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, data, duration).show()

val Context.getScreenWidth: Int get() = resources.displayMetrics.widthPixels
