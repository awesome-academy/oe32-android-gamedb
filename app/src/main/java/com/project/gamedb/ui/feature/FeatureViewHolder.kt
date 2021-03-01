package com.project.gamedb.ui.feature

import android.view.View
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.Games
import com.project.gamedb.ultis.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_feature.view.*

class FeatureViewHolder(private val itemView: View) : BaseViewHolder<Games>(itemView),
    View.OnClickListener {
    private var itemFeature: Games? = null

    override fun onBind(item: Games) {
        itemFeature = item
        itemView.run {
            textItemFeature.text = item.gameName
            gameItemFeature.loadImage(item.gameBackgroundImage)
        }
    }

    override fun onClick(v: View?) {}
}
