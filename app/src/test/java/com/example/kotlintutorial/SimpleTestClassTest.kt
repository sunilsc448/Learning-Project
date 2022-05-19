package com.example.kotlintutorial

import KotlinSamples.CouroutineSamples
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SimpleTestClassTest {

    @Before
    fun setUp(){

    }

    @After
    fun tearDown() {
    }

    @Test
    fun add() = runBlocking{
        CouroutineSamples().myOwnSuspendFunction()
        Assert.assertEquals(4, 2 + 2)
    }
}