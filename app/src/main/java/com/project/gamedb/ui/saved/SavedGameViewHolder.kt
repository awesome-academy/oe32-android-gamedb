package com.project.gamedb.ui.saved

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import com.project.gamedb.R
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.ultis.PlatformConstants
import com.project.gamedb.ultis.loadImage
import com.project.gamedb.ultis.setPlatform
import kotlinx.android.synthetic.main.item_recyclerview_saved.view.*

class SavedGameViewHolder(private val itemView: View) : BaseViewHolder<GameSaved>(itemView) {
    private var itemSaved: GameSaved? = null
    private var savedFragment = SavedGameFragment.getInstance()

    override fun onBind(item: GameSaved) {
        itemSaved = item
        itemView.run {
            setPlatform(this, getPlatform(item.platform))
            gameSavedName.text = item.name
            savedBackground.loadImage(item.url)
            textMetaScoreSaved.text =
                context.getString(
                    R.string.meta_score_point,
                    if (item.score > 0) item.score.toString() else 0
                )
            releasedDateSaved.text = context.getString(R.string.released_date, item.date)
            textGenresSaved.text = context.getString(R.string.genres, item.genres)
            textCommentCount.text = item.reviewCount
            onClickMode(this)
            this.setOnLongClickListener {
                savedFragment.chooseState = true
                boxChecked.visibility = CheckBox.VISIBLE
                SavedGameFragment.removeList.add(item)
                it.isSelected = true
                savedFragment.handleDeleteButton()
                true
            }
        }
    }

    private fun setPlatform(view: View, platforms: List<String>) {
        val imageSaved = listOf<ImageView>(
            view.image1stFavPlatform,
            view.image2ndFavPlatform,
            view.image3rdFavPlatform,
            view.image4thFavPlatform,
            view.image5thFavPlatform
        )
        for (i in platforms.indices) {
            imageSaved[i].setPlatform(platforms[i])
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

    private fun onClickMode(view: View) {
        view.setOnClickListener {
            if (savedFragment.chooseState) {
                view.isSelected = !view.isSelected
                handleClickedView(view)
                savedFragment.chooseState = SavedGameFragment.removeList.size != 0
                savedFragment.handleDeleteButton()
            } else {
                itemSaved?.let { savedFragment.openGameDetail(it.id, it.genres) }
            }
        }
    }

    private fun handleClickedView(view: View) {
        if (view.isSelected) {
            itemSaved?.let { it1 -> SavedGameFragment.removeList.add(it1) }
            view.boxChecked.visibility = CheckBox.VISIBLE
        } else {
            itemSaved?.let { it1 -> SavedGameFragment.removeList.remove(it1) }
            view.boxChecked.visibility = CheckBox.GONE
        }
    }
}
