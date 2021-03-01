package com.project.gamedb.ultis

import android.content.res.Resources
import android.view.View

fun View.OnClickListener.assignViews(vararg views: View?) {
    views.forEach { it?.setOnClickListener(this) }
}

fun View.getScreenWidth(element: Int): View =
    this.also {
        it.layoutParams.width = Resources.getSystem().displayMetrics.widthPixels / element
    }
