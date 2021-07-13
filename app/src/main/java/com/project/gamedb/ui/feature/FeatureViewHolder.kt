package com.project.gamedb.ui.feature

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

class FeatureViewHolder(
    private val itemView: View,
    private val anOnClickFragmentListener: OnClickFragmentListener.Details
) :
    BaseViewHolder<Games>(itemView) {

    override fun onBind(item: Games) {
        itemView.run {
            setPlatform(this, getPlatform(item.gamePlatforms.toString()))
            textItemFeature.text = item.gameName
            gameItemFeature.loadImage(item.gameBackgroundImage)
            textGenresFeature.text = getListGenres(item.gameGenres)
            this.setOnClickListener {
                anOnClickFragmentListener.openGameDetail(
                    item.gameId.toInt(),
                    getListGenres(item.gameGenres)
                )
            }
        }
    }

    private fun setPlatform(view: View, platforms: List<String>) {
        val imageSaved = listOf<ImageView>(
            view.image1stItemPlatform,
            view.image2ndItemPlatform,
            view.image3rdItemPlatform,
            view.image4thItemPlatform,
            view.image5thItemPlatform
        )
        for (i in platforms.indices) {
            imageSaved[i].setPlatform(platforms[i])
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

    private fun getListGenres(genres : List<Genres>): String{
        val list = mutableListOf<String>()
        for (gen in genres){
            list.add(gen.genresName)
        }
        return list.toString().replace("[","").replace("]","")
    }
}
