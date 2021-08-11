package com.edu.cs554.airlinesreservationsystem.config;

import com.edu.cs554.airlinesreservationsystem.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true, //--enables @Secured annotation
        jsr250Enabled = true, //--enables @RolesAllowed annotation
        prePostEnabled = true //--enables @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter annotation
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/email").permitAll()
                .antMatchers("/passenger/create").permitAll()
                .antMatchers("/agent/create").permitAll()
                .antMatchers("/admin/create").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/flights").permitAll()
                .antMatchers("/flights/{id}").permitAll()
                .antMatchers("/flights/{id}").permitAll()
                .antMatchers("/reservation").permitAll()
                .antMatchers("/reservation/{id}").permitAll()
                .antMatchers("/airports").permitAll()
                .antMatchers("/airports/{id}").permitAll()
                .antMatchers("/airlines").permitAll()
                .antMatchers("/confirm/{currentReservation}").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
