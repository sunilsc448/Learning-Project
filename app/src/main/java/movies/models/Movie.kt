package movies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    val id: Int?,
    val title: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val popularity: Double?,
    @SerializedName("vote_average")
    val voteAverage: Float?):Serializable