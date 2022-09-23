package serializableVsParcelable

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SerializableVsParcelable {

}

class SampleSerializableClass:Serializable{
    @SerializedName("first_name")private var firstName:String = ""
    @SerializedName("second_name")private var lastName:String = ""
}

class SampleParcelableClass() :Parcelable{
    private var firstName:String? = null
    private var lastName:String? = null

    constructor(parcel: Parcel) : this() {
        firstName = parcel.readString()
        lastName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SampleParcelableClass> {
        override fun createFromParcel(parcel: Parcel): SampleParcelableClass {
            return SampleParcelableClass(parcel)
        }

        override fun newArray(size: Int): Array<SampleParcelableClass?> {
            return Array(size){SampleParcelableClass()}
        }
    }
}