package com.xuefei.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GateWayConfig {

    // 编码方式实现路由
//    @Bean
    public RouteLocator orderRoute(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_route1",
                predicateSpec -> predicateSpec.path("/payment/query/**")
                        .uri("http://localhost:8001")).build();
        return routes.build();
    }


}
