package com.vdbo.rateitia.movies.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.vdbo.core.data.movie.Movie
import com.vdbo.rateitia.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import kotlin.properties.Delegates

class MoviesAdapter(
    private val onMovieClick: ((item: Movie) -> Unit)?
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val diffCallback = DiffItemCallback()
    private val diffHelper = AsyncListDiffer(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder<Movie>(diffCallback).build()
    )
    private val items: List<Movie>
        get() = diffHelper.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view, onMovieClick)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setMovies(movies: List<Movie>) {
        diffHelper.submitList(movies)
    }

    class ViewHolder(
        override val containerView: View,
        onMovieClick: ((item: Movie) -> Unit)?
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        var item: Movie by Delegates.notNull()

        init {
            onMovieClick?.let { listener ->
                containerView.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) listener.invoke(item)
                }
            }
        }

        fun bind(item: Movie) {
            this.item = item
            title.text = item.title
            description.text = item.description
            rating.text = String.format("%.1f", item.rating)
        }

    }

    class DiffItemCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.javaClass == newItem.javaClass && oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

    }

}