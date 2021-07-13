package com.project.gamedb.ui.ranking

import android.view.View
import android.widget.ImageView
import com.project.gamedb.R
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.model.Games
import com.project.gamedb.data.model.Genres
import com.project.gamedb.ui.saved.SavedGameFragment
import com.project.gamedb.ultis.*
import kotlinx.android.synthetic.main.item_recyclerview_ranking.view.*

class RankingViewHolder(
    private val itemView: View,
    private val OnClickFragmentListener: OnClickFragmentListener.Details
) : BaseViewHolder<Games>(itemView) {

    private val saveCallback = SavedGameFragment.getInstance()
    private var itemData: Games? = null

    init {
        itemView.setOnClickListener {
            itemData?.let {
                OnClickFragmentListener.openGameDetail(
                    it.gameId.toInt(),
                    getListGenres(it.gameGenres)
                )
            }
        }
    }

    override fun onBind(item: Games) {
        itemData = item
        itemView.run {
            imageFav.apply {
                isClickable = if (SavedGameFragment.itemList.contains(item.gameName)) {
                    setImageResource(R.drawable.ic_not_saved)
                    false
                } else {
                    setImageResource(R.drawable.ic_favourite)
                    saveGame(this, item)
                    true
                }
            }
            textRankingGame.text = item.gameName
            imageRanking.loadImage(item.gameBackgroundImage)
            textComment.text = item.gameReviewsCounts
            val platforms = getPlatform(item.gamePlatforms.toString())
            setPlatform(this, platforms)
            this.setOnClickListener {
                OnClickFragmentListener.openGameDetail(
                    item.gameId.toInt(),
                    getListGenres(item.gameGenres)
                )
            }
        }
    }

    private fun setPlatform(view: View, platforms: List<String>) {
        val imageRanking = listOf<ImageView>(
            view.image1stRankingPlatform,
            view.image2ndRankingPlatform,
            view.image3rdRankingPlatform,
            view.image4thRankingPlatform,
            view.image5thRankingPlatform
        )
        for (i in platforms.indices) {
            imageRanking[i].setPlatform(platforms[i])
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

    private fun getListGenres(genres: List<Genres>): String {
        val list = mutableListOf<String>()
        for (gen in genres) {
            list.add(gen.genresName)
        }
        return list.toString().replace("[", "").replace("]", "")
    }

    private fun saveGame(imageView: ImageView, games: Games) {
        imageView.apply {
            setOnClickListener {
                saveCallback.addGame(
                    GameSaved(
                        games.gameId.toInt(),
                        games.gameName,
                        getListGenres(games.gameGenres),
                        games.gameBackgroundImage,
                        games.gameReleased,
                        games.gamePlatforms.toString(),
                        games.gameMetaCritic,
                        games.gameReviewsCounts
                    )
                )
                this.setImageResource(R.drawable.ic_not_saved)
                isClickable = false
            }
        }
    }
}
