package movies.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(@SerializedName("page")val page:Int,
                          @SerializedName("results") val movies:List<Movie>,
                          @SerializedName("total_pages") private val total_pages:Int,
                          @SerializedName("total_results") private val total_results:Int)