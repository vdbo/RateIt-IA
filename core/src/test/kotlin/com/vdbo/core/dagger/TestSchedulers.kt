package com.vdbo.core.dagger

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestSchedulers @Inject constructor() : SchedulerProvider {

    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

}