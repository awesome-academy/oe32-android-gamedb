package com.project.gamedb.ultis

import android.content.Context
import android.content.res.Resources
import android.view.View

fun View.OnClickListener.assignViews(vararg views: View?) {
    views.forEach { it?.setOnClickListener(this) }
}

fun View.setScreenWidth(ratio: Double) {
    this.layoutParams.width = (context.getScreenWidth * ratio).toInt()
}
