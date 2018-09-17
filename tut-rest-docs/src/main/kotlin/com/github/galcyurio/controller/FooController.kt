package com.github.galcyurio.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author galcyurio
 */
@RestController
class FooController {

    @GetMapping
    fun index(): ResponseEntity<Any> {
        return ResponseEntity.ok("index")
    }
}