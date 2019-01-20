package com.vdbo.rateitia.movies.overview

import com.vdbo.core.data.movie.SortType

data class MoviesOverviewViewData(
    val sortType: SortType
) {

    companion object {
        val DEFAULT = MoviesOverviewViewData(SortType.DESC)
    }

}