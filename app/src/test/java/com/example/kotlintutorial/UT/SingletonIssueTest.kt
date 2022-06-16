package com.example.kotlintutorial.UT

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class SingletonIssueTest {
    private lateinit var SUT:SingletonIssue
    @Before
    fun setUp() {
        SUT = SingletonIssue()
    }

    @Test
    fun getTotalSteps() {
        SUT.walk()
        SUT.walk()
        SUT.run()
        SUT.run()
        val steps = SUT.getTotalSteps()

        assertEquals(steps, 12)
    }

    @Test
    fun walk() {
        SUT.walk()
        val steps = SUT.getTotalSteps()
        assertEquals(steps, 2)
    }

    @Test
    fun run() {
        SUT.run()
        val steps = SUT.getTotalSteps()
        assertEquals(steps, 4)
    }
}