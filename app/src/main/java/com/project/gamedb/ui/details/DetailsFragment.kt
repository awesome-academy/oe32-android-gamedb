package com.project.gamedb.ui.details

import android.content.Context
import android.widget.Toast
import androidx.core.os.bundleOf
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ultis.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment(private val idGame: Int, private val genres: String) : BaseFragment(),
    DetailsContract.View {
    private var detailsGamePresenter: DetailsPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Close? = null

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
    }

    override fun showGameDetail(game: GameDetail) {
        textGameDetailName?.text = game.name
        context?.let { layoutDetails?.loadDrawable(it, game.backgroundImage) }
        textDetailGenres?.text = genres
        textReleasedDate?.text = game.released
        textRating?.text = game.rating.toString()
        textMetaScore?.text = game.metacritic.toString()
        textDetailDescription?.text = game.description
        imagePublisherDetail?.loadImage(game.backgroundImageAdditional)
        textPlatformDetail?.text = getPlatform(game.platforms.toString()).toString()
            .replace("]", "")
            .replace("[", "")
        textUpdatedDetail?.text = game.updated
        textPlaytime?.text = getString(R.string.string_playtime, game.playtime.toString())
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_LONG)
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        detailsGamePresenter = DetailsPresenter(this, repository)
    }

    private fun getPlatform(strings: String): List<String> {
        val platforms = mutableListOf<String>()
        for (string in PlatformConstants.platforms) {
            if (strings.contains(string)) platforms.add(string)
        }
        return platforms
    }
}
