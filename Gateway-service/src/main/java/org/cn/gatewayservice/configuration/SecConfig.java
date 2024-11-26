package org.cn.gatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity

public class SecConfig {

    private RsaConfig rsaConfig;

    public SecConfig(RsaConfig rsaConfig) {
        this.rsaConfig = rsaConfig;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(auth -> auth
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/ENSEIGNANT-SERVICE/Enseignants/email/{email}").permitAll()
                        .pathMatchers("/ENSEIGNANT-SERVICE/Enseignants/**").hasAuthority("SCOPE_Enseignant")
                        .pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/email/{email}").permitAll()
                        .pathMatchers("/CHERCHEUR-SERVICE/Chercheurs/**").hasAnyAuthority("SCOPE_Chercheur", "SCOPE_Enseignant")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }


    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        // Ensure that rsaConfig.publicKey() returns a valid PublicKey
        return NimbusReactiveJwtDecoder.withPublicKey(rsaConfig.publicKey()).build();
    }


}
