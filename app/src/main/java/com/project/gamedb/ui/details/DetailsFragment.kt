package com.project.gamedb.ui.details

import android.app.AlertDialog
import android.content.Context
import android.text.Html
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.saved.SavedGameFragment
import com.project.gamedb.ultis.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment(private val idGame: Int, private val genres: String) : BaseFragment(),
    DetailsContract.View {
    private var detailsGamePresenter: DetailsPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Close? = null
    private var saveCallback = SavedGameFragment.getInstance()
    private var dialog: AlertDialog? = null

    override val layoutResource: Int
        get() = R.layout.fragment_details

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Close) mainCallback = context
    }

    override fun startComponents() {
        initPresenter()
        detailsGamePresenter?.getGameDetail(idGame.toLong())
        buttonBack.setOnClickListener { mainCallback?.pressBackButton() }
        dialog = createDialog()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
        layoutDetails?.hide()
    }

    override fun showGameDetail(game: GameDetail) {
        dialog?.dismiss()
        layoutDetails?.show()
        if (SavedGameFragment.itemList.contains(game.name)) {
            buttonFollow.text = getString(R.string.text_followed)
            buttonFollow.isClickable = false
        }
        textGameDetailName?.text = game.name
        context?.let { layoutDetails?.loadDrawable(it, game.backgroundImage) }
        textDetailGenres?.text = genres
        textReleasedDate?.text = game.released
        textRating?.text = game.rating.toString()
        textMetaScore?.text = game.metacritic.toString()
        textDetailDescription?.text = Html.fromHtml(game.description)
        imagePublisherDetail?.loadImage(game.backgroundImageAdditional)
        textPlatformDetail?.text = getPlatform(game.platforms.toString())
        textUpdatedDetail?.text = game.updated.substring(0, 10)
        textPlaytime?.text = getString(R.string.string_playtime, game.playtime.toString())
        saveGame(game)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_LONG)
        mainCallback?.pressBackButton()
        dialog?.hide()
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        detailsGamePresenter = DetailsPresenter(this, repository)
    }

    private fun getPlatform(strings: String): String {
        val platforms = mutableListOf<String>()
        for (string in PlatformConstants.platforms) {
            platforms.filter { strings.contains(string) }
        }
        return platforms.joinToString { it }
    }

    private fun saveGame(game: GameDetail) {
        buttonFollow?.apply {
            setOnClickListener {
                saveCallback.addGame(
                    GameSaved(
                        game.id.toInt(),
                        game.name,
                        genres,
                        game.backgroundImage,
                        game.released,
                        getPlatform(game.platforms.toString()),
                        game.metacritic,
                        game.ratingCount.toString()
                    )
                )
                text = getString(R.string.text_followed)
                isClickable = false
            }
        }
    }

    private fun createDialog(): AlertDialog {
        val builder = AlertDialog.Builder(context).setMessage(R.string.text_Loading)
        return builder.create()
    }
}
