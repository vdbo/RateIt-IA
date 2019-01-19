package com.vdbo.rateitia.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.vdbo.rateitia.R
import com.vdbo.rateitia.RApplication

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProviders.of(this, RApplication.appComponent.viewModelFactory)
            .get(MainViewModel::class.java)
    }
    private val component: MainComponent by lazy(LazyThreadSafetyMode.NONE) {
        RApplication.appComponent
            .mainComponentBuilder()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel.getMovies()
    }

}
