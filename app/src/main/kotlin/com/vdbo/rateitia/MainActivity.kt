package com.vdbo.rateitia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vdbo.rateitia.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private val component: MainComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerMainComponent.builder()
            .coreComponent(RApplication.coreComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val vm = ViewModelProviders.of(this, component.viewModelFactory)
            .get(MainViewModel::class.java)
        vm.getMovies()
    }

}
