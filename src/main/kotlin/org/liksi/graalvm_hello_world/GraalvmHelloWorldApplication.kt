package org.liksi.graalvm_hello_world

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraalvmHelloWorldApplication

fun main(args: Array<String>) {
    runApplication<GraalvmHelloWorldApplication>(*args)
}

