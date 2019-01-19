package com.vdbo.rateitia.movies.overview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.vdbo.rateitia.R
import com.vdbo.rateitia.RApplication
import kotlinx.android.synthetic.main.activity_movies_overview.*

class MoviesOverviewActivity : AppCompatActivity() {

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies_overview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sort_desc -> TODO()
        R.id.action_sort_asc -> TODO()
        else -> true
    }

    private fun setUpUi() {
        setSupportActionBar(toolbar)
    }

}
