package com.project.gamedb.ultis

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.project.gamedb.R

fun ImageView.loadImage(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .transform(CenterCrop(), RoundedCorners(25))
        .into(this)
}
