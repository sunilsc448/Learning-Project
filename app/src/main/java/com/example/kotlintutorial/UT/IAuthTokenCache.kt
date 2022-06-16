package com.example.kotlintutorial.UT

interface IAuthTokenCache {
    fun cacheAuthToken(authToken: String)
    fun getAuthToken(): String
}