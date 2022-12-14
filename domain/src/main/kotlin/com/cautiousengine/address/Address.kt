package com.cautiousengine.address

import java.time.Instant

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val createdAt: Instant = Instant.now()
)
