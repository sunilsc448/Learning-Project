package com.example.kotlintutorial.UT

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.hamcrest.CoreMatchers.`is`
import org.mockito.*


private const val ORDER_ID = "123456"
private const val AMOUNT = 1001

@RunWith(MockitoJUnitRunner::class)
class AddToCartUseCaseTest{
    private lateinit var SUT:AddToCartUseCase
    @Mock
    private lateinit var addtoCartMock:IAddToCart

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        SUT = AddToCartUseCase(addtoCartMock)
    }

    @Captor
    private lateinit var ac:ArgumentCaptor<CartItem>
    @Test
    fun addToCart_success_paramsPassed(){
        ac = ArgumentCaptor.forClass(CartItem::class.java)
        SUT.addToCart(ORDER_ID, AMOUNT)
        Mockito.verify(addtoCartMock).addToCart(capture(ac))
//        Mockito.verify(addtoCartMock).addToCart(ac.capture())
        assertThat(ac.value.orderId, `is`(ORDER_ID))
        assertThat(ac.value.amount, `is`(AMOUNT))
    }

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}
