package com.cautiousengine.addresses.ktor.routes

import com.cautiousengine.address.RandomAddressGenerator
import com.cautiousengine.address.generator.faker.FakerRandomAddressGenerator
import com.cautiousengine.addresses.ktor.models.AddressesResponse
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.addressRouting() {
    route("/addresses") {
        get {
            val numAddresses = call.request.queryParameters["num-addresses"] ?: "5"

            val addressGenerator: RandomAddressGenerator = FakerRandomAddressGenerator()
            val addresses = addressGenerator
                .generate(numAddresses = numAddresses.toInt())
                .map {
                    AddressesResponse.Address(
                        street = it.street,
                        city = it.city,
                        state = it.state,
                        zipCode = it.zipCode,
                        createdAt = it.createdAt
                    )
                }

            call.respond(AddressesResponse(addresses))
        }
    }
}
