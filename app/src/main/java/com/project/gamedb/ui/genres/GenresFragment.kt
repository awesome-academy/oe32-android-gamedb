package com.project.gamedb.ui.genres

import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.data.model.ResultGenres
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_genres.*

class GenresFragment : BaseFragment(), GenresContract.View {
    override val layoutResource: Int
        get() = R.layout.fragment_genres

    private var genresPresenter: GenresPresenter? = null
    private val genresAdapter = GenresAdapter()

    override fun startComponents() {
        initPresenter()
        initAdapter()
        initData()
    }

    override fun showGenres(list: ResultGenres) {
        genresAdapter.replaceData(list.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    private fun initData() {
        genresPresenter?.getGenres()
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        genresPresenter = GenresPresenter(this, repository)
    }

    private fun initAdapter() {
        recyclerGenres.adapter = genresAdapter
    }
}
