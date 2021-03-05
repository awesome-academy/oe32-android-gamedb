package com.project.gamedb.ui.details

import android.widget.Toast
import androidx.core.os.bundleOf
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.loadDrawable
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment(private val idGame: Int, private val genres: String) : BaseFragment(),
    DetailsContract.View {
    private var detailsGamePresenter: DetailsPresenter? = null

    override val layoutResource: Int
        get() = R.layout.fragment_details

    override fun startComponents() {
        initPresenter()
        detailsGamePresenter?.getGameDetail(idGame.toLong())
    }

    override fun showGameDetail(game: GameDetail) {}

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_LONG)
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        detailsGamePresenter = DetailsPresenter(this, repository)
    }
}
