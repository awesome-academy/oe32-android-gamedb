package com.project.gamedb.ui.popular

import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.feature.FeatureAdapter
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : BaseFragment(), PopularContract.View {
    private var mainPresenter: PopularPresenter? = null
    private val mainAdapter = PopularAdapter()
    private val itemAdapter = FeatureAdapter()

    override val layoutResource: Int
        get() = R.layout.fragment_popular

    override fun initData() {
        initPresenter()
        mainPresenter?.apply {
            getMainGameFeature()
            getItemGameFeature(getString(R.string.string_order_rating))
        }
    }

    override fun initAdapter() {
        recyclerGameFeature.adapter = mainAdapter
        recyclerGameItem.adapter = itemAdapter
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        mainPresenter = PopularPresenter(this, repository)
    }

    override fun showGame(games: ResultGames) {
        val list = games.results
        mainAdapter.replaceData(list)
    }

    override fun showItemGame(games: ResultGames) {
        val list = games.results
        itemAdapter.replaceData(list)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_LONG)
    }

}
