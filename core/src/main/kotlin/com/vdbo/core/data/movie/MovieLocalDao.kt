package com.vdbo.core.data.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieLocalDao {

    companion object {
        const val SCRIPT_PREFILL = "INSERT INTO movie " +
                "(title, description, rating) VALUES " +
                "(\"The Shawshank Redemption\", \"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\", \"9.3\"), " +
                "(\"The Godfather\", \"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.\", \"9.2\"), " +
                "(\"The Dark Knight\", \"When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.\", \"9.0\"), " +
                "(\"The Godfather: Part II\", \"The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.\", \"9.0\"), " +
                "(\"The Lord of the Rings: The Return of the King\", \"Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.\", \"8.9\"), " +
                "(\"Pulp Fiction\", \"The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.\", \"8.9\"), " +
                "(\"Schindler's List\", \"In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazi Germans.\", \"8.9\"), " +
                "(\"The Good, the Bad and the Ugly\", \"A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.\", \"8.9\"), " +
                "(\"12 Angry Men\", \"A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence.\", \"8.9\"), " +
                "(\"Inception\", \"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.\", \"8.8\")"
    }

    @Query("SELECT * FROM movie ORDER BY rating DESC")
    fun getDesc(): Single<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY rating ASC")
    fun getAsc(): Single<List<Movie>>

    @Update
    fun edit(movie: Movie): Completable

}