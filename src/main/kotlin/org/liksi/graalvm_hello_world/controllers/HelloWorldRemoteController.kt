package org.liksi.graalvm_hello_world.controllers

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.getForObject
import java.time.Instant

@RestController
class HelloWorldRemoteController {

    @GetMapping("/hello-remote")
    fun helloRemote(): String {
        val apiResponse = RestTemplateBuilder()
            .build()
            .getForObject<HelloRemote>("http://localhost:8080/hello")

        return "${apiResponse.message} : with timestamp ${apiResponse.metadata?.timestamp}"
    }
}

@Configuration
@RegisterReflectionForBinding(
    HelloRemote::class,
)
class RuntimeConfiguration

data class HelloRemote(
    val message: String,
    val metadata: Metadata?,
) {
    data class Metadata(val timestamp: Instant)
}
