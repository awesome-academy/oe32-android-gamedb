package com.project.gamedb.ui.popular

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ui.feature.FeatureAdapter
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : BaseFragment(), PopularContract.View, OnClickFragmentListener.Details {
    override val layoutResource get() = R.layout.fragment_popular

    private var mainAdapter: PopularAdapter = PopularAdapter(this)
    private var itemAdapter: FeatureAdapter = FeatureAdapter(this)
    private var mainPresenter: PopularPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initAdapter()
        initData()
        LinearSnapHelper().attachToRecyclerView(recyclerGameFeature)
    }

    override fun showGame(games: ResultGames) {
        textFeatureLoading?.hide()
        mainAdapter.replaceData(games.results)
    }

    override fun showItemGame(games: ResultGames) {
        textItemLoading?.hide()
        itemAdapter.replaceData(games.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    override fun openGameDetail(id: Int, genres: String) {
        mainCallback?.openNewFragment(DetailsFragment(id, genres))
    }

    private fun initData() {
        initPresenter()
        mainPresenter?.apply {
            getMainGameFeature()
            getItemGameFeature(getString(R.string.string_order_rating))
        }
    }

    private fun initAdapter() {
        recyclerGameFeature.adapter = mainAdapter
        recyclerGameItem.adapter = itemAdapter
        buttonSeeAll.setOnClickListener {
            mainCallback?.openNewFragment(
                MoreFragment.newInstance(
                    getString(R.string.string_order_rating),
                    getString(R.string.string_ordering)
                )
            )
        }
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        mainPresenter = PopularPresenter(this, repository)
    }
}
