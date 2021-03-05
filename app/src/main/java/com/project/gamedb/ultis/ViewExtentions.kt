package com.project.gamedb.ultis

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget

fun View.OnClickListener.assignViews(vararg views: View?) {
    views.forEach { it?.setOnClickListener(this) }
}

fun View.setScreenWidth(ratio: Double) {
    this.layoutParams.width = (context.getScreenWidth * ratio).toInt()
}

fun View.loadDrawable(context: Context, urlImage: String) {
    if (this.isVisible) {
        Glide.with(context).load(urlImage)
            .apply(RequestOptions().override(this.width, this.height)).transform(CenterCrop())
            .into(object :
                CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    this@loadDrawable.background = resource
                }
            })
    }
}
