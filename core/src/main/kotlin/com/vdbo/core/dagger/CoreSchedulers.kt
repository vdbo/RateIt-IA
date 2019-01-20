package com.vdbo.core.dagger

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CoreSchedulers @Inject constructor() : SchedulerProvider {

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

}