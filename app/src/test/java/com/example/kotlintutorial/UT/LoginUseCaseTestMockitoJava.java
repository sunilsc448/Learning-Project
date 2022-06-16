package com.example.kotlintutorial.UT;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LoginUseCaseTestMockitoJava {
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";
    private static final String AUTH_TOKEN = "authToken";

    private LoginUseCase SUT;

    @Mock private ILoginHttpEndpoint loginHttpEndPointMock;
    @Mock private IAuthTokenCache authTokenCacheMock;
    @Mock private IEventBusPost eventBusPostMock;
    @Mock private INetworkManager networkManagerMock;

    @Before
    public void setup() throws Exception {
        SUT = new LoginUseCase(loginHttpEndPointMock,authTokenCacheMock, eventBusPostMock, networkManagerMock);
        success();
    }

    @Test
    public void loginSync_success_userNamePasswordPaasedToEndPoint() throws Exception{
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        SUT.login(USERNAME, PASSWORD);
        verify(loginHttpEndPointMock, times(1)).login(argumentCaptor.capture(), argumentCaptor.capture());
        List<String> captures = argumentCaptor.getAllValues();
        assertThat(captures.get(0), is(USERNAME));
        assertThat(captures.get(1), is(PASSWORD));
    }

    private void success() throws Exception{
        Mockito.when(loginHttpEndPointMock.login(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(new EndPointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN));
    }
}
