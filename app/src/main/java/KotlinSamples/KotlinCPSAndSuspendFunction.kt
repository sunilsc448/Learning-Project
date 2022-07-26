package KotlinSamples

import android.content.Context
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

//CPS - Continuation Processing Style i.e callbacks
class KotlinCPS {
    init {
        postItem(item = Item())

        val job: Job = CoroutineScope(Dispatchers.IO).launch{

        }
    }

    //Direct Style - Sequentially continue based on prev actions
    fun postItem(item:Item):Post{
        val token = getToken()
        val post = createPost(item, token)
        return processPost(post)
    }

    //Pseudo cod
    //Continuation Style - pass the result to next statement as continuation
//    requestToken{ token ->
//        createPost(item, token): post ->{
//            processPost(post)
//        }
//    }

    //hidden Continuation<Post> cont in all suspend functions
    //JVM Object postItemContinuation(item:Item, Continuation<Post> cont){...}
//    interface Continuation<T>{
//        val context:CoroutineContext
//        fun resume(value:T)
//        fun resumeWithException(exception:Throwable)
//    }
    //1. Initial Continuation > for entire function
    //2. token Continuation
    //3. createPost Continuation
    suspend fun postItemContinuation(item:Item):Post{
        val token = getToken()
        val post = createPost(item, token)
        return processPost(post)
    }


    private fun processPost(post: JSONObject):Post {
        Thread.sleep(1000)
        return Post("Succesfully Posted")
    }

    private fun createPost(item: Item, token: String): JSONObject {
       Thread.sleep(1000)
        val jsonObj = JSONObject()
        jsonObj.put("token", token)
        jsonObj.put("item", item)
       return jsonObj
    }

    private fun getToken(): String {
        Thread.sleep(1000)
        return "token"
    }
}

class Item{

}

class Post(val post:String){

}