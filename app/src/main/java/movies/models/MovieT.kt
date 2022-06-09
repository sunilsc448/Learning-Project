package movies.models
import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "Movies")
data class MovieT(
    @PrimaryKey @NonNull
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float?){
    constructor(id: Int, title: String?, overview: String?, poster_path: String?, releaseDate: String?,
                originalTitle: String?,originalLanguage: String?, popularity: Double?, voteAverage: Float?, dummy:Int = 0) :
            this(id, title, overview, poster_path, releaseDate,originalTitle,originalLanguage, popularity, voteAverage)
}