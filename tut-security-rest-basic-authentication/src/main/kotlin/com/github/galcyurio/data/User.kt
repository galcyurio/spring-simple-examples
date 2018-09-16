package com.github.galcyurio.data

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

/**
 * @author galcyurio
 */
@Entity
data class User(
    private val username: String,
    private val password: String,

    @ElementCollection
    private val authorities: Collection<GrantedAuthority>,

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
) : UserDetails {
    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities.toMutableSet()
    override fun isEnabled(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}