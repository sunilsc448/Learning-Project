package movies.models

enum class MoviesLoadingState {
  LOADING,
  ERROR,
  LOADED,
  INVALID_API_KEY
}