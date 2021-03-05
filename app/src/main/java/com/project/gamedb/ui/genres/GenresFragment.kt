package com.project.gamedb.ui.genres

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.base.OnClickMoreListener
import com.project.gamedb.data.model.ResultGenres
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.data.source.remote.api.ApiConstants
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_genres.*

class GenresFragment : BaseFragment(), GenresContract.View, OnClickMoreListener {
    private var genresPresenter: GenresPresenter? = null
    private var genresAdapter: GenresAdapter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override val layoutResource: Int
        get() = R.layout.fragment_genres

    init {
        genresAdapter = GenresAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loaded = savedInstanceState?.getBoolean(getString(R.string.text_Loading))
        if (loaded == true) textGenresLoading.hide() else textGenresLoading.text =
            getString(R.string.text_Loading)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(getString(R.string.text_Loading), true)
    }

    override fun startComponents() {
        initAdapter()
        initPresenter()
        initData()
    }

    override fun showGenres(list: ResultGenres) {
        textGenresLoading?.hide()
        genresAdapter?.replaceData(list.results)
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

    override fun openMoreFragment(id: String) {
        mainCallback?.openNewFragment(
            MoreFragment(
                id,
                ApiConstants.GENRES
            )
        )
    }
}
