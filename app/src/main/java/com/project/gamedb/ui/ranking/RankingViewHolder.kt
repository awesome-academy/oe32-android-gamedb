package com.project.gamedb.ui.ranking

import android.view.View
import android.widget.ImageView
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.Games
import com.project.gamedb.ultis.*
import kotlinx.android.synthetic.main.item_recyclerview_ranking.view.*

class RankingViewHolder(private val itemView: View) : BaseViewHolder<Games>(itemView) {
    private var itemFeature: Games? = null

    override fun onBind(item: Games) {
        itemFeature = item
        itemView.run {
            textRankingGame.text = item.gameName
            imageRanking.loadImage(item.gameBackgroundImage)
            textComment.text = item.gameReviewsCounts
            val platforms = getPlatform(item.gamePlatforms.toString())
            val imageRanking = listOf<ImageView>(
                image1stRankingPlatform,
                image2ndRankingPlatform,
                image3rdRankingPlatform,
                image4thRankingPlatform,
                image5thRankingPlatform
            )
            for (i in platforms.indices) {
                imageRanking[i].setPlatform(platforms[i])
            }
        }
    }

    private fun getPlatform(strings: String): List<String> {
        val platforms = mutableListOf<String>()
        for (string in PlatformConstants.platforms) {
            if (strings.contains(string)) platforms.add(string)
        }
        if (platforms.contains(PlatformConstants.IOS) && platforms.contains(PlatformConstants.ANDROID)) {
            platforms.remove(PlatformConstants.IOS)
        }
        return platforms
    }
}
