package com.example.mylibrary

import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.text.Charsets.UTF_8

object NetworkUtils {
    class Request(internal val method: METHOD) {
        internal var url: String? = null
        internal var header: MutableMap<String, String> = HashMap()
        internal var body: ByteArray? = null
        private var reqListener: JSONObjectListener? = null
        private var threadExecutor = ThreadExecutor()

        fun url(url:String): Request {
            this.url = url
            return this
        }

        fun header(header: Map<String, String>?): Request {
            if (header.isNullOrEmpty()) return this
            this.header.putAll(header)
            return this
        }

        fun body(jsonObject: JSONObject?): Request {
            if (jsonObject == null) return this
            val text = jsonObject.toString()
            this.body = text.toByteArray(charset(UTF_8.toString()))
            this.header.put("Content-Type", "application/json")
            return this
        }

        internal fun sendResponse(resp: Response?, e: Exception?) {
            if (reqListener != null) {
                if (e != null) reqListener?.onError(e)
                else reqListener?.onResponse(resp?.asJsonObject())
            }
        }

        fun callBackListner(jsonObjectListener: JSONObjectListener?):Request{
            this.reqListener = jsonObjectListener
            return this
        }

        fun request() {
            threadExecutor.execute(RequestTask(this))
        }
    }

    private const val BUFFER_SIZE = 4096
    internal class RequestTask(private val request: Request) : Runnable {
        override fun run() {
            try {
                val connection = request()
                val parsedResponse = parseResponse(connection)
                request.sendResponse(parsedResponse, null)
            } catch (e: Exception) {
                request.sendResponse(null, e)
            }
        }

        @Throws(IOException::class)
        fun request(): HttpURLConnection {
            val url = URL(request.url)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = request.method.toString()
            request.header.forEach {
                connection.setRequestProperty(it.key, it.value)
            }
            request.body?.let {
                connection.outputStream.write(it)
            }
            connection.connect()
            return connection
        }

        @Throws(IOException::class)
        fun parseResponse(connection: HttpURLConnection): Response {
            val bos = ByteArrayOutputStream()
            try {
                val status = connection.responseCode
                val validStatus = status in 200..299
                val inputStream =
                    if (validStatus) connection.inputStream else connection.errorStream
                var read: Int
                val buf = ByteArray(BUFFER_SIZE)
                while (inputStream.read(buf).also { read = it } != -1) {
                    bos.write(buf, 0, read)
                }
                return Response(bos.toByteArray())
            } finally {
                bos.close()
                connection.disconnect()
            }
        }

    }

    internal class Response(private val data: ByteArray) {
        @Throws(JSONException::class)
        fun asJsonObject(): JSONObject{
            val resposneString = String(data, UTF_8)
            return if (resposneString.isEmpty())
                JSONObject()
            else
                JSONObject(resposneString)
        }
    }

    enum class METHOD {
        GET,
        POST,
        PUT,
        DELETE
    }

    interface JSONObjectListener {
        fun onResponse(jsonObject: JSONObject?)
        fun onError(e: Exception?)
    }

    internal class ThreadExecutor {
        fun execute(runnable: Runnable?) {
//            val thread = HandlerThread("myHandlerThread")
//            val handler = Handler(thread.looper)
//            handler.post(runnable!!)
            val thread = Thread(runnable)
            thread.start()
        }
    }
}