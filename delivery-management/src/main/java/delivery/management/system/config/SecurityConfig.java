package delivery.management.system.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static delivery.management.system.model.enums.PermissionType.*;
import static delivery.management.system.model.enums.RoleType.*;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        String ADMINS = "admins/";
        String ROLE = "admin/roles/**";
        String DRIVERS = "drivers/";
        String DRIVERS_UPDATED = "drivers-updated/{id}";
        String DRIVERS_DELETED = "drivers-deleted/{id}";
        String CUSTOMER = "users/customer";
        String LOGIN = "users/authentication";
        String LOGOUT = "users/logout";
        String FIND_BY_ID = "find-by-id";
        String UPDATED = "updated";
        String CONFIRMATION = "/users/confirmation";
        String RESET_PASSWORD = "/users/reset-password";
        String RENEW_PASSWORD = "/users/renew-password/{username}";
        String SWAGGER_UI = "/swagger-ui/**";
        String API_DOCS = "/v3/api-docs/**";

        http
                .csrf(CsrfConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurer()))
                .sessionManagement(sessionConfigure -> sessionConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST, CUSTOMER).permitAll();
                    request.requestMatchers(HttpMethod.DELETE, CUSTOMER).hasAuthority(CUSTOMER_DELETE.getName());
                    request.requestMatchers(HttpMethod.GET, CUSTOMER).hasAuthority(CUSTOMER_READ.getName());
                    request.requestMatchers(HttpMethod.PUT, CUSTOMER).hasAuthority(CUSTOMER_PUT.getName());
                    request.requestMatchers(HttpMethod.PATCH, CUSTOMER).hasAuthority(CUSTOMER_PATCH.getName());
                    request.requestMatchers(HttpMethod.POST, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.PATCH, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.GET, ROLE).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.POST, LOGIN).permitAll();
                    request.requestMatchers(HttpMethod.POST, CONFIRMATION).permitAll();
                    request.requestMatchers(HttpMethod.POST, LOGOUT).authenticated();
                    request.requestMatchers(HttpMethod.POST, RENEW_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.PUT, RESET_PASSWORD).permitAll();
                    request.requestMatchers(HttpMethod.GET, DRIVERS + FIND_BY_ID).hasRole(DRIVER.name());
                    request.requestMatchers(HttpMethod.PUT, DRIVERS + UPDATED).hasRole(DRIVER.name());
                    request.requestMatchers(HttpMethod.PUT, ADMINS + DRIVERS_UPDATED).hasRole(ADMIN.name());
                    request.requestMatchers(HttpMethod.DELETE, ADMINS + DRIVERS_DELETED).hasRole(ADMIN.name());
                    request.requestMatchers(SWAGGER_UI).permitAll();
                    request.requestMatchers(API_DOCS).permitAll();
                    request.anyRequest().permitAll();
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    private UrlBasedCorsConfigurationSource corsConfigurer() {
        CorsConfiguration corsConfigure = new CorsConfiguration();
        corsConfigure.addAllowedOrigin("*");
        corsConfigure.addAllowedHeader("*");
        corsConfigure.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfigure);

        return corsConfigurationSource;
    }
}
