package com.ofengx.cloudserverzuul.remote.hystrix;

import com.ofengx.cloudserverzuul.bean.JwtTokeInfo;
import com.ofengx.cloudserverzuul.remote.SecurityService;
import org.springframework.stereotype.Component;

@Component
public class SecurityHystrix implements SecurityService {

    @Override
    public JwtTokeInfo verification(String token) {
        return new JwtTokeInfo();
    }
}
