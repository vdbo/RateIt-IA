package com.vdbo.rateitia.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    internal val disposables = CompositeDisposable()
    internal val viewDisposables = CompositeDisposable()

    internal open fun onViewActive() {
        //no-op
    }

    @CallSuper
    internal open fun onViewNotActive() {
        viewDisposables.clear()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}