package com.github.galcyurio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TutSecurityRestBasicAuthenticationApplication

fun main(args: Array<String>) {
    runApplication<TutSecurityRestBasicAuthenticationApplication>(*args)
}
