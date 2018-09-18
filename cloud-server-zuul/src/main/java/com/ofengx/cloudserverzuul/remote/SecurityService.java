package com.ofengx.cloudserverzuul.remote;

import com.ofengx.cloudserverzuul.bean.JwtTokeInfo;
import com.ofengx.cloudserverzuul.remote.hystrix.SecurityHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cloud-server-security",
        path = "/token",
        fallback = SecurityHystrix.class
)
public interface SecurityService {

    @GetMapping("/verification/{token}")
    JwtTokeInfo verification(@PathVariable("token") String token);

}
