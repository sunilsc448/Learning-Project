package KotlinSamples

import androidx.work.Operation
import java.lang.Exception

class KotlinDestructionDeclarationSamples {
    init {
        //kind of similar function like sealed class
        val(result, status, exception) = RepoClass().getResponseDataClass(RepoClass.Status.FAILURE)
        println("result > $result, status > $status, exception > $exception")

        val(result1, status1, exception1) = RepoClass().getResponseNormalClass(RepoClass.Status.SUCCESS)
        println("result1 > $result1, status1 > $status1, exception1 > $exception1")

        val map = mapOf("sunil" to 33, "anil" to 34, "pooja" to 31)
        for ((key, value) in map) {
           println("key > $key, value > $value")
        }
    }
}

data class ResponseDataDatClass(val result: String?, val status:Int, val exception:Exception?){

}

class ResponseDataNormalClass(val result: String?, val status:Int, val exception:Exception?) {
    operator fun component1(): String? {
        return result
    }
    operator fun component2(): Int {
        return status
    }
    operator fun component3(): Exception? {
        return exception
    }
}

class RepoClass(){
    fun getResponseDataClass(status:Status):ResponseDataDatClass{
        if(status.equals(Status.SUCCESS)){
            return ResponseDataDatClass("Response data", 200, null)
        }else{
            return ResponseDataDatClass(null, 502, Exception("Gateway Error"))
        }
    }

    fun getResponseNormalClass(status:Status):ResponseDataNormalClass{
        if(status.equals(Status.SUCCESS)){
            return ResponseDataNormalClass("Response data", 200, null)
        }else{
            return ResponseDataNormalClass(null, 502, Exception("Gateway Error"))
        }
    }

    enum class Status{
        SUCCESS,
        FAILURE
    }
}