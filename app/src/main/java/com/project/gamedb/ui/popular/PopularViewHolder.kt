package com.project.gamedb.ui.popular

import android.view.View
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.Games
import com.project.gamedb.data.model.Genres
import com.project.gamedb.ultis.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_main.view.*

class PopularViewHolder(
    private val itemView: View,
    private val anOnClickFragmentListener: OnClickFragmentListener.Details
) :
    BaseViewHolder<Games>(itemView) {

    override fun onBind(item: Games) {
        itemView.run {
            textGenresFeature.text = getListGenres(item.gameGenres)
            textGameFeature.text = item.gameName
            imageFeature.loadImage(item.gameBackgroundImage)
            buttonMoreInfo.setOnClickListener {
                anOnClickFragmentListener.openGameDetail(
                    item.gameId.toInt(),
                    getListGenres(item.gameGenres)
                )
            }
        }
    }

    private fun getListGenres(genres : List<Genres>): String{
        val list = mutableListOf<String>()
        for (gen in genres){
            list.add(gen.genresName)
        }
        return list.toString().replace("[","").replace("]","")
    }
}
