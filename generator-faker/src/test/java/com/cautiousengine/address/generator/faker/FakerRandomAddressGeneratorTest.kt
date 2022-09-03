package com.cautiousengine.address.generator.faker

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

internal class FakerRandomAddressGeneratorTest {
    private val generator = FakerRandomAddressGenerator()

    @Nested
    inner class Generate {
        @Test
        fun `generates random addresses`() {
            val result = generator.generate(min = 1)
            assertTrue(result.isNotEmpty())
        }

        @Test
        fun `generates exact number of addresses using same min and max`() {
            val result = generator.generate(min = 3, max = 3)
            assertEquals(3, result.size)
        }

        @Test
        fun `supports returning empty list`() {
            val result = generator.generate(min = 0, max = 0)
            assertTrue(result.isEmpty())
        }

        @Test
        fun `defaults 'min' to 0`() {
            val result = generator.generate(max = 0)
            assertEquals(0, result.size)
        }

        @Test
        fun `defaults 'max' to 10`() {
            val result = generator.generate(min = 10)
            assertEquals(10, result.size)
        }

        @Test
        fun `throws InvalidArgumentException when 'min' is less than 0`() {
            val throwable = assertFails {
                generator.generate(min = -1)
            }

            assertEquals("'min' must be >= 0", throwable.message)
        }
    }
}