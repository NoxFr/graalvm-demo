package org.liksi.graalvm_hello_world.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class HelloWorldController {

    data class Hello(
        val message: String,
        val metadata: Metadata = Metadata(),
    ) {
        data class Metadata(val timestamp: Instant = Instant.now())
    }

    @GetMapping("/hello")
    fun hello(@RequestParam(name = "name", defaultValue = "World") name: String) = Hello(
        message = "Hello $name",
    )
}
