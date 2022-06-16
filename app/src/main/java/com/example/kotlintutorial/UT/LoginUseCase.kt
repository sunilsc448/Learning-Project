package com.example.kotlintutorial.UT

import java.lang.Exception

class LoginUseCase(val loginHttpEndpoint: ILoginHttpEndpoint, val authTokenCache:IAuthTokenCache, val eventBusPost: IEventBusPost, val networkManager:INetworkManager){
    fun login(userName:String, password:String):LoginUseCaseResult{
        if(!networkManager.isNetwork()){
            return LoginUseCaseResult.NETWORK_ERROR
        }
        var endPointResult:EndPointResult? = null
        try{
            endPointResult = loginHttpEndpoint.login(userName, password)
        }catch(e:Exception){
            return LoginUseCaseResult.FAILURE
        }

        if(isSuccessfulEndpointResult(endPointResult!!)){
            eventBusPost.postEvent(LogEvent("Response received"))
            authTokenCache.cacheAuthToken(endPointResult.authToken)
            return LoginUseCaseResult.SUCCESS
        }else{
            return LoginUseCaseResult.FAILURE
        }
    }

    private fun isSuccessfulEndpointResult(endpointResult: EndPointResult): Boolean {
        return endpointResult.status === EndpointResultStatus.SUCCESS
    }
}

enum class LoginUseCaseResult {
    SUCCESS,
    FAILURE,
    NETWORK_ERROR
}