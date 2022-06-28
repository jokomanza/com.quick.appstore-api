package com.quick.plugins

import com.quick.model.App
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.http.content.*
import io.ktor.server.locations.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.util.UUID


val apps = listOf(App(UUID.randomUUID().toString(), "App Store", "com.quick.appstore"))


fun Application.configureRouting() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }

    }

    install(Locations) {
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }

        get("/apps") {

            call.respond(HttpStatusCode.OK, apps)
        }

        get("/app/{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            val app = apps.find { it.id == id }
                ?: return@get call.respond(HttpStatusCode.BadRequest, "App not found")

            call.respond(HttpStatusCode.OK, app)
        }

        // Register nested routes
        /*get<App> {
            call.respondText("Inside $it")
        }*/
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()


/*@Location("/app/{id}")
data class App(val name: String) {

    @Location("/edit")
    data class Edit(val app: App)


*//*
    @Location("/list/{page}")
    data class List(val app: App, val page: Int)*//*
}*/
