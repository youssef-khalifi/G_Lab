package org.cn.gatewayservice;

import org.cn.gatewayservice.configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    /*@Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route(r->r
                .path("/Enseignants")
                .filters(f->f.addRequestHeader("token", "hhhhhhhhhhhh"))
                .uri("http://localhost:8081"))
                .route(r->r
                        .path("/Chercheurs")
                        .filters(f->f.addRequestHeader("token", "hhhhhhhhhhhh"))
                        .uri("http://localhost:8082"))

                .route(r->r
                                .path("/Projets")
                                .filters(f->f.addRequestHeader("token", "hhhhhhhhhhhh"))
                                .uri("http://localhost:8083"))
                .build();
    }*/

    @Bean
    DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return  new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }


}
