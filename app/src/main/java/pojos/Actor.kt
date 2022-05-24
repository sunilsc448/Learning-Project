package pojos

import com.google.gson.annotations.SerializedName

data class Actor(@SerializedName("name")var name: String,
                 @SerializedName("fullName") var fullName:String,
                 @SerializedName("age") var age:Int,
                 @SerializedName("Born At") var bornAt:String,
                 @SerializedName("Birthdate") var birthDate:String,
                 @SerializedName("photo") var photo:String,
                 @SerializedName("industry") var industry:String)
