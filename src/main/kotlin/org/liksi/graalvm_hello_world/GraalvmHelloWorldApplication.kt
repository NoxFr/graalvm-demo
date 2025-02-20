package org.liksi.graalvm_hello_world

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import org.springframework.web.client.getForObject
import java.time.Instant

@SpringBootApplication
class GraalvmHelloWorldApplication

fun main(args: Array<String>) {
    runApplication<GraalvmHelloWorldApplication>(*args)
}

@RestController
class HelloWorldController {

    data class Hello(val message: String)

    @GetMapping("/hello")
    fun hello(@RequestParam(name = "name", defaultValue = "World") name: String) = Hello("Hello $name")
}


@RestController
class HelloWorldRemoteController {

    data class HelloDto(
        val message: String,
        val metadata: Metadata
    ) {
        data class Metadata(val timestamp: Instant = Instant.now())
    }

    @GetMapping("/hello-remote")
    fun helloRemote(): String? {
        val apiResponse = RestTemplateBuilder()
            .build()
            .getForObject<HelloDto>("http://localhost:8080/hello")

        return apiResponse.message
    }
}

@Configuration
@RegisterReflectionForBinding(
    HelloWorldRemoteController.HelloDto::class,
    HelloWorldRemoteController.HelloDto.Metadata::class)
class RuntimeConfiguration