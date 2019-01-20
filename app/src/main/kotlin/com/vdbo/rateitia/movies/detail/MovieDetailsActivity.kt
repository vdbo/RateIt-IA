package com.vdbo.rateitia.movies.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vdbo.rateitia.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_MOVIE_ID = "movie_id"
        const val REQUEST_CODE = 1

        fun newIntent(context: Context, movieId: Int) =
            Intent(context, MovieDetailsActivity::class.java)
                .apply { putExtra(EXTRA_MOVIE_ID, movieId) }

    }

    private val movieId by lazy(LazyThreadSafetyMode.NONE) {
        intent.getIntExtra(EXTRA_MOVIE_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpUi()
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_submit -> TODO()
        else -> true
    }

    private fun setUpUi() {
        setSupportActionBar(toolbar)
    }

}