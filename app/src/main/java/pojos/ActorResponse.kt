package pojos

import com.google.gson.annotations.SerializedName

data class ActorResponse(@SerializedName("Actors") var actors:List<Actor>)