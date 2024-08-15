package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
  /*
   * @Bean
   * public InMemoryUserDetailsManager userDetailsManager() {
   * UserDetails john = User.builder()
   * .username("john")
   * .password("{noop}test123")
   * .roles("EMPLOYEE")
   * .build();
   * 
   * UserDetails mary = User.builder()
   * .username("mary")
   * .password("{noop}test123")
   * .roles("EMPLOYEE", "MANAGER")
   * .build();
   * 
   * UserDetails susan = User.builder()
   * .username("susan")
   * .password("{noop}test123")
   * .roles("EMPLOYEE", "MANAGER", "ADMIN")
   * .build();
   * 
   * return new InMemoryUserDetailsManager(john, mary, susan);
   * }
   */

  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // define query to retrieve a user by username
    jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

    // define query to retrieve roles/authorities by username
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        "SELECT user_id, role FROM roles WHERE user_id=?");

    return jdbcUserDetailsManager;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(configurer -> configurer
        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
        .requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN"));

    // Use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaults());

    // Disable CSRF
    // In general, not required for stateless APIs that use POST, PUT, DELETE,
    // and/or PATCH requests
    http.csrf(csrf -> csrf.disable());

    return http.build();
  }
}
