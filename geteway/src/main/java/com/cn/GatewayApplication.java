package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

/*    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "http://localhost:4001/role/list";
        return builder.routes()
                .route(p -> p
                        .path("/roleList")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback(){
        return Mono.just("启用降级服务");
    }*/

}
