package com.example.kotlintutorial.UT

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import java.lang.Exception

private const val USERNAME = "Sunil"
private const val PASSWORD = "KumarSC"
private const val AUTH_TOKEN = "authToken"

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTestMockito {
    private lateinit var SUT:LoginUseCase
    @Mock private lateinit var loginHttpEndPointMock:ILoginHttpEndpoint
    @Mock private lateinit var authTokenCacheMock: IAuthTokenCache
    @Mock private lateinit var eventBusPostMock: IEventBusPost
    @Mock private lateinit var networkManagerMock: INetworkManager

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        loginHttpEndPointMock = Mockito.mock(ILoginHttpEndpoint::class.java)
//        authTokenCacheMock = Mockito.mock(IAuthTokenCache::class.java)
//        eventBusPostMock = Mockito.mock(IEventBusPost::class.java)
//        networkManagerMock = Mockito.mock(INetworkManager::class.java)
        SUT = LoginUseCase(loginHttpEndPointMock, authTokenCacheMock, eventBusPostMock, networkManagerMock)
        mockSuccess()
    }

    private fun mockSuccess() {
        `when`(loginHttpEndPointMock.login(any(String::class.java), any(String::class.java))).thenReturn(EndPointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN))
    }

//    @Test
//    fun login_success_usernameAndPassword(){
//        SUT.login(USERNAME, PASSWORD)
//        Assert.assertEquals(loginHttpEndPointTD.mUsername, USERNAME)
//        Assert.assertEquals(loginHttpEndPointTD.mPassword, PASSWORD)
//    }

    @Captor
    private lateinit var ac:ArgumentCaptor<String>
    @Test
    fun login_authToken_check(){
        ac = ArgumentCaptor.forClass(String::class.java)
        SUT.login(USERNAME, PASSWORD)
        verify(loginHttpEndPointMock, times(1)).login(capture(ac), capture(ac))
        val captures = ac.allValues
        assertEquals(captures[0], USERNAME)
        assertEquals(captures[1], PASSWORD)
    }

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

//    @Test
//    fun login_success_eventCount(){
//        SUT.login(USERNAME, PASSWORD)
//        SUT.login(USERNAME, PASSWORD)
//        assertEquals(eventBusPostTD.eventsCount, 2)
//    }
}