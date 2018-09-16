package com.github.galcyurio.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author galcyurio
 */
@RestController
class FooController {

    @GetMapping("/public")
    fun public(): String {
        return "none security"
    }

    @GetMapping("/protected")
    fun protected(): String {
        return "security"
    }
}