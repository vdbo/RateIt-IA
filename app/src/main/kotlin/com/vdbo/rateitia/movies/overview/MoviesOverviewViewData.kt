package com.vdbo.rateitia.movies.overview

import com.vdbo.core.common.RandomRatingsManager
import com.vdbo.core.common.SortType

data class MoviesOverviewViewData(
    val sortType: SortType,
    val randomRatingsState: RandomRatingsManager.State
) {

    companion object {
        val DEFAULT = MoviesOverviewViewData(SortType.DESC, RandomRatingsManager.State.STOPPED)
    }

}