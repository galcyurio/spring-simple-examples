package com.github.galcyurio.config

import com.github.galcyurio.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter

/**
 * @author galcyurio
 */
class DigestWebSecurityConfig(
    val userService: UserService
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        // TODO: dev 일 경우만 H2 관련 설정하기
        http.authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .and()
            .csrf().ignoringAntMatchers("/h2-console/**")
            .and()
            .headers().frameOptions().sameOrigin()

        http.authorizeRequests()
            .antMatchers("/public").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(digestFilter(), BasicAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint(digestEntryPoint())
    }

    @Bean fun digestFilter(): DigestAuthenticationFilter {
        return DigestAuthenticationFilter().apply {
            userDetailsService = userService
            setAuthenticationEntryPoint(digestEntryPoint())
        }
    }

    @Bean fun digestEntryPoint(): DigestAuthenticationEntryPoint {
        return DigestAuthenticationEntryPoint().apply {
            key = "dummy"
            realmName = "Contacts Realm via Digest Authentication"
        }
    }

    @Bean fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}