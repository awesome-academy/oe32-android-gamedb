package com.project.gamedb.ui.search

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.base.OnQuerySearchListener
import com.project.gamedb.data.model.Genres
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.show
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment(), SearchContract.View, OnQuerySearchListener {
    private var searchPresenter: SearchPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override val layoutResource: Int
        get() = R.layout.fragment_search

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initPresenter()
    }

    override fun showGame(games: ResultGames) {
        textSearchLoading?.hide()
        val listGames = games.results.map { it.gameName }
        listView?.adapter =
            context?.let { ArrayAdapter(it, R.layout.item_listview_search, listGames) }
        listView?.setOnItemClickListener { _, _, position, id ->
            context?.let { hideKeyboard(it) }
            mainCallback?.openNewFragment(
                DetailsFragment(
                    games.results[position].gameId.toInt(),
                    getListGenres(games.results[position].gameGenres)
                )
            )
        }
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    override fun querySearch(text: String) {
        listView?.adapter = null
        searchPresenter?.getSearchedGame(text)
        textSearchLoading?.text = getString(R.string.text_Loading)
        textSearchLoading?.show()
    }

    private fun hideKeyboard(context: Context){
        try {
            val inputManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val view: View? = (context as Activity).currentFocus
            if (view != null) {
                inputManager.hideSoftInputFromWindow(
                    view.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        searchPresenter = SearchPresenter(this, repository)
    }

    private fun getListGenres(genres: List<Genres>): String = genres.joinToString { it.genresName }
}
