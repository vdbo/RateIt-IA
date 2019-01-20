package com.vdbo.core.dagger

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

}