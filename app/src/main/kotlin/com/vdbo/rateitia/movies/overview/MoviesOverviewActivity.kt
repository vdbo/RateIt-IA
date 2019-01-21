package com.vdbo.rateitia.movies.overview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vdbo.rateitia.R
import com.vdbo.rateitia.RApplication
import com.vdbo.rateitia.movies.detail.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movies_overview.*

class MoviesOverviewActivity : AppCompatActivity() {

    private val moviesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MoviesAdapter { viewModel.onMovieClick(it) }
    }
    private val viewModel: MoviesOverviewViewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProviders.of(this, RApplication.appComponent.viewModelFactory)
            .get(MoviesOverviewViewModel::class.java)
    }
    private val component: MoviesOverviewComponent by lazy(LazyThreadSafetyMode.NONE) {
        RApplication.appComponent
            .moviesOverviewComponentBuilder()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_overview)
        setUpUi()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            MovieDetailsActivity.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) viewModel.onMovieEdited()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies_overview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sort_desc -> {
            viewModel.onDescSortChosen()
            true
        }
        R.id.action_sort_asc -> {
            viewModel.onAscSortChosen()
            true
        }
        R.id.action_random_rating -> {
            viewModel.onRandomRatingChosen()
            true
        }
        else -> true
    }

    private fun setUpUi() {
        setSupportActionBar(toolbar)
        movies.adapter = moviesAdapter

        viewModel.movies
            .observe(this, Observer {
                moviesAdapter.setMovies(it)
            })

        viewModel.navigateToMovieDetails
            .observe(this, Observer { event ->
                event.consume {
                    startActivityForResult(
                        MovieDetailsActivity.newIntent(this, it.id),
                        MovieDetailsActivity.REQUEST_CODE
                    )
                }
            })
    }

}
