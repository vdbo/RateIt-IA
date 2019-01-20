package com.vdbo.rateitia.movies.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding3.widget.ratingChanges
import com.vdbo.rateitia.R
import com.vdbo.rateitia.RApplication
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_MOVIE_ID = "movie_id"
        const val REQUEST_CODE = 1

        fun newIntent(context: Context, movieId: Int) =
            Intent(context, MovieDetailsActivity::class.java)
                .apply { putExtra(EXTRA_MOVIE_ID, movieId) }

    }

    private val viewModel: MovieDetailsViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, RApplication.appComponent.viewModelFactory)
            .get(MovieDetailsViewModel::class.java)
    }
    private val movieId by lazy(LazyThreadSafetyMode.NONE) {
        intent.getIntExtra(EXTRA_MOVIE_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpUi()
        observeChanges()
        viewModel.onCreate(movieId)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewActive()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewNotActive()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.action_submit -> {
            viewModel.onSubmit()
            true
        }
        else -> true
    }

    private fun setUpUi() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.rating = rating.ratingChanges()
            .skipInitialValue()
            .share()
    }

    private fun observeChanges() {
        viewModel.movie.observe(this, Observer {
            mainTitle.text = it.title
            description.text = it.description
            rating.rating = it.rating
        })

        viewModel.navigateOnSubmit.observe(this, Observer {
            it.consume {
                setResult(RESULT_OK)
                finish()
            }
        })
    }

}