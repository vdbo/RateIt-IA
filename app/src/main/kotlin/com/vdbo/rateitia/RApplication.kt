package com.vdbo.rateitia

import android.app.Application
import com.vdbo.core.dagger.CoreComponent
import com.vdbo.core.dagger.CoreModule
import com.vdbo.core.dagger.DaggerCoreComponent

class RApplication : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
    }

}