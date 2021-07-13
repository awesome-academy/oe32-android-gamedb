package com.project.gamedb.ui.genres

import android.view.View
import com.project.gamedb.R
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.Genres
import com.project.gamedb.ultis.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_genres.view.*

class GenresViewHolder(
    private val itemView: View,
    private val OnClickMoreListener: OnClickFragmentListener.More
) :
    BaseViewHolder<Genres>(itemView) {

    override fun onBind(item: Genres) {
        itemView.run {
            textGenres.isEnabled = false
            textGenres.setText(item.genresName)
            imageGenres.loadImage(item.genresImageBackground)
            textGenresCount.text =
                context.getString(R.string.format_popular_items, item.genresGamesCount.toString())
            this.setOnClickListener { OnClickMoreListener.openMoreFragment(item.genresId.toString()) }
        }
    }
}
