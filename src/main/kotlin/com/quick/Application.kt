package com.quick

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.quick.plugins.*

fun main() {
    embeddedServer(Netty, port = 3000, host = "0.0.0.0") {
        configureAdministration()
        configureRouting()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        // configureSecurity()
    }.start(wait = true)
}
