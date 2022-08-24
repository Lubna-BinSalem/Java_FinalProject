package com.example.bookatable.Config;

import com.example.bookatable.Service.MyUserDetailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailService myUserDetailService;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
           //     .antMatchers(HttpMethod.POST,"/api/v1/user/register").permitAll()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/user/admin").hasAuthority("admin")
                .antMatchers(HttpMethod.GET,"/api/v1/booking/all").hasAuthority("admin")
                .antMatchers(HttpMethod.GET,"/api/v1/review/all").hasAuthority("admin")
                .antMatchers(HttpMethod.GET,"/api/v1/user/user").hasAuthority("user")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
    }
}
