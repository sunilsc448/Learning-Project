package KotlinSamples

import com.example.mylibrary.NetworkUtils
import org.json.JSONObject
import java.lang.Exception
import java.util.*

class NetoworkLibUseCase {
    val listener = object : NetworkUtils.JSONObjectListener{
        override fun onResponse(jsonObject: JSONObject?) {
            println(jsonObject)
        }

        override fun onError(e: Exception?) {
            println(e)
        }

    }
    init {
//        val url = "https://demo5982810.mockable.io/actors?industry=all&priority=sandalwood" //get
        val url = "http://demo5982810.mockable.io/actors" //post
        val headers = mapOf("key1" to "value1", "key2" to "value2")
        val jsonObj = JSONObject()
        jsonObj.put("name", "Sunil")
        jsonObj.put("age", 30)

        NetworkUtils.Request(NetworkUtils.METHOD.POST).
                    url(url).
                    header(headers).
                    body(jsonObj).
                    callBackListner(listener).
                    request()
    }
}