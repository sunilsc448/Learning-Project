package com.example.kotlintutorial.UT

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

private const val USERNAME = "Sunil"
private const val PASSWORD = "KumarSC"
private const val AUTH_TOKEN = "authToken"

class LoginUseCaseTest {
    private lateinit var SUT:LoginUseCase
    private lateinit var loginHttpEndPointTD:LoginHttpEndPointTD
    private lateinit var authTokenCacheTD: AuthTokenCacheTD
    private lateinit var eventBusPostTD: EventBusPostTD
    private lateinit var networkManagerTD: NetworkManagerTD
    @Before
    fun setUp() {
        loginHttpEndPointTD = LoginHttpEndPointTD()
        authTokenCacheTD = AuthTokenCacheTD()
        eventBusPostTD = EventBusPostTD()
        networkManagerTD = NetworkManagerTD()

        SUT = LoginUseCase(loginHttpEndPointTD, authTokenCacheTD, eventBusPostTD, networkManagerTD)
    }

    @After
    fun tearDown() {
        //nothing to clear here
    }

    @Test
    fun login_success_usernameAndPassword(){
        SUT.login(USERNAME, PASSWORD)
        assertEquals(loginHttpEndPointTD.mUsername, USERNAME)
        assertEquals(loginHttpEndPointTD.mPassword, PASSWORD)
    }

    @Test
    fun login_authToken_check(){
        SUT.login(USERNAME, PASSWORD)
        assertEquals(authTokenCacheTD.mAuthToken, AUTH_TOKEN)
    }

    @Test
    fun login_success_eventCount(){
        SUT.login(USERNAME, PASSWORD)
        SUT.login(USERNAME, PASSWORD)
        assertEquals(eventBusPostTD.eventsCount, 2)
    }
}

class LoginHttpEndPointTD:ILoginHttpEndpoint {
    var mUsername = ""
    var mPassword = ""
    override fun login(username: String, password: String): EndPointResult {
        mUsername = username
        mPassword = password
        return EndPointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN)
    }
}

class AuthTokenCacheTD:IAuthTokenCache{
    var mAuthToken = ""
    override fun cacheAuthToken(authToken: String) {
        mAuthToken = authToken
    }

    override fun getAuthToken(): String {
        return mAuthToken
    }
}

class EventBusPostTD:IEventBusPost{
    var eventsCount = 0
    override fun postEvent(event: Any) {
        eventsCount++
    }
}

class NetworkManagerTD:INetworkManager{
    override fun isNetwork(): Boolean {
        return true
    }
}

