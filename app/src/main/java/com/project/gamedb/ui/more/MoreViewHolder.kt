package com.project.gamedb.ui.more

import android.view.View
import android.widget.ImageView
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.Games
import com.project.gamedb.data.model.Genres
import com.project.gamedb.ultis.PlatformConstants
import com.project.gamedb.ultis.loadImage
import com.project.gamedb.ultis.setPlatform
import kotlinx.android.synthetic.main.item_recyclerview_feature.view.*
import kotlinx.android.synthetic.main.item_recyclerview_feature.view.textGenresFeature
import kotlinx.android.synthetic.main.item_recyclerview_main.view.*

class MoreViewHolder(
    private val itemView: View,
    private val onClickDetailsListener: OnClickFragmentListener.Details
) : BaseViewHolder<Games>(itemView) {
    private var itemData: Games? = null

    init {
        itemView.setOnClickListener {
            itemData?.let {
                onClickDetailsListener.openGameDetail(
                    it.gameId.toInt(),
                    getListGenres(it.gameGenres)
                )
            }
        }
    }

    override fun onBind(item: Games) {
        itemData = item
        itemView.run {
            setPlatform(this, getPlatform(item.gamePlatforms.toString()))
            textGenresFeature.text = getListGenres(item.gameGenres)
            textItemFeature?.text = item.gameName
            gameItemFeature?.loadImage(item.gameBackgroundImage)
        }
    }

    private fun setPlatform(view: View, platforms: List<String>) {
        val image = listOf<ImageView>(
            view.image1stItemPlatform,
            view.image2ndItemPlatform,
            view.image3rdItemPlatform,
            view.image4thItemPlatform,
            view.image5thItemPlatform
        )
        for (i in platforms.indices) {
            image[i].setPlatform(platforms[i])
        }
    }

    private fun getPlatform(strings: String): List<String> {
        val platforms = mutableListOf<String>()
        for (string in PlatformConstants.platforms) {
            platforms.filter { strings.contains(string) }
        }
        if (platforms.contains(PlatformConstants.IOS) && platforms.contains(PlatformConstants.ANDROID)) {
            platforms.remove(PlatformConstants.IOS)
        }
        return platforms
    }

    private fun getListGenres(genres: List<Genres>): String = genres.joinToString { it.genresName }
}
