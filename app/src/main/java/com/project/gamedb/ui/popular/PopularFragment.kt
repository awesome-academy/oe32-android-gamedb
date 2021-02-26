package com.project.gamedb.ui.popular

import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.showToast

class PopularFragment : BaseFragment(), PopularContract.View {
    private var mainPresenter: PopularPresenter? = null

    override val layoutResource: Int
        get() = R.layout.fragment_popular

    override fun initData() {
        initPresenter()
        mainPresenter?.apply {
            getMainGameFeature()
            getItemGameFeature()
        }
    }

    override fun initAdapter() {

    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        mainPresenter = PopularPresenter(this, repository)
    }

    override fun showGame(games: ResultGames) {
    }

    override fun showItemGame(games: ResultGames) {
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_LONG)
    }

}
