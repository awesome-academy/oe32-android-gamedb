package com.project.gamedb.ui.popular

import android.view.View
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.Games
import com.project.gamedb.ultis.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_main.view.*

class PopularViewHolder(private val itemView: View) : BaseViewHolder<Games>(itemView) {
    override fun onBind(item: Games) {
        itemView.run {
            textGameFeature.text = item.gameName
            imageFeature.loadImage(item.gameBackgroundImage)
        }
    }
}
