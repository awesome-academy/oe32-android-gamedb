package com.project.gamedb.ui.ranking

import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_ranking.*
import java.util.*

class RankingFragment : BaseFragment(), RankingContract.View {
    override val layoutResource: Int
        get() = R.layout.fragment_ranking

    private val rankingAdapter = RankingAdapter()
    private var rankingPresenter: RankingPresenter? = null

    override fun startComponents() {
        initPresenter()
        initAdapter()
        initData()
    }

    override fun showGame(list: ResultGames) {
        rankingAdapter.replaceData(list.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    private fun initData() {
        rankingPresenter?.getRankedGame(Calendar.getInstance().get(Calendar.YEAR) - 1)
    }

    private fun initAdapter() {
        recyclerRanking.adapter = rankingAdapter
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        rankingPresenter = RankingPresenter(this, repository)
    }
}
