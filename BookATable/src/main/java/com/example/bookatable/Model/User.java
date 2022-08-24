package com.example.bookatable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.hibernate.query.criteria.internal.predicate.PredicateImplementor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor @NoArgsConstructor @Data @Entity
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotEmpty(message = "Username must not be empty")
    @Column(unique = true)
    @Size(min=3,message = "Username has to be at least 3 characters long")
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Size(min=5,message = "Username has to be at least 5 characters long")
    private String password;

    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Phone number must not be empty")
    @Pattern(regexp = "[0-9]*[0-9]",message = "must be a valid phone number")
    private String phoneNumber;

   // @Column(columnDefinition = "varchar(10) check (role='user' | role='admin')")
    @Pattern(regexp = "admin|user", message = "role must be either admin or user")
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
