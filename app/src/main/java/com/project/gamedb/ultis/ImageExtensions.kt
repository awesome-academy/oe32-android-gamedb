package com.project.gamedb.ultis

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.project.gamedb.R

fun ImageView.loadImage(urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .apply(RequestOptions().override(this.width, this.height))
        .placeholder(R.drawable.image_loading)
        .error(R.drawable.image_error)
        .transform(CenterCrop(), RoundedCorners(25))
        .into(this)
}

fun ImageView.setPlatform(platform: String) {
    when (platform) {
        PlatformConstants.PC -> setImageResource(R.drawable.ic_pc)
        PlatformConstants.NINTENDO -> setImageResource(R.drawable.ic_nintendo)
        PlatformConstants.PLAYSTATION -> setImageResource(R.drawable.ic_playstation)
        PlatformConstants.XBOX -> setImageResource(R.drawable.ic_xbox)
        PlatformConstants.ANDROID, PlatformConstants.IOS -> setImageResource(R.drawable.ic_phone)
    }
}
