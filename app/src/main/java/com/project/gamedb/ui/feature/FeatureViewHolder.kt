package com.project.gamedb.ui.feature

import android.view.View
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickDetailsListener
import com.project.gamedb.data.model.Games
import com.project.gamedb.ultis.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_feature.view.*

class FeatureViewHolder(
    private val itemView: View,
    private val onClickDetailsListener: OnClickDetailsListener
) :
    BaseViewHolder<Games>(itemView) {

    override fun onBind(item: Games) {
        itemView.run {
            textItemFeature.text = item.gameName
            gameItemFeature.loadImage(item.gameBackgroundImage)
            this.setOnClickListener {
                onClickDetailsListener.openGameDetail(
                    item.gameId.toInt(),
                    item.gameGenres.toString()
                )
            }
        }
    }
}
