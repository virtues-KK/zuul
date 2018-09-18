package com.ofengx.cloudserverzuul

import com.ofengx.cloudserverzuul.filter.TokenFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.ofengx.cloudserverzuul.remote")
class CloudServerZuulApplication {

    @Bean
    TokenFilter tokenFilter() {
        return new TokenFilter()
    }

    static void main(String[] args) {
        SpringApplication.run(CloudServerZuulApplication.class, args)
    }
}
