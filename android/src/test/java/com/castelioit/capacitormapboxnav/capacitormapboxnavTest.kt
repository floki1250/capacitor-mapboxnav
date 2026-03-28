package com.castelioit.capacitormapboxnav

import org.junit.Assert.assertEquals
import org.junit.Test

class capacitormapboxnavTest {

    @Test
    fun echo_isCorrect() {
        val implementation = capacitormapboxnav()
        val value = "Hello, World!"
        val result = implementation.echo(value)
        assertEquals(value, result)
    }
}
