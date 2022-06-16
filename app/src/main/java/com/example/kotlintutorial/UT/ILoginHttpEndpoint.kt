package com.example.kotlintutorial.UT

import android.accounts.NetworkErrorException

interface ILoginHttpEndpoint{
    @Throws(NetworkErrorException::class)
    fun login(username:String = "", password:String = ""):EndPointResult?
}

data class EndPointResult(val status:EndpointResultStatus, val authToken:String)

enum class EndpointResultStatus{
    SUCCESS,
    AUTH_ERROR,
    SERVER_ERROR
}