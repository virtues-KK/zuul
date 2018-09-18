package com.ofengx.cloudserverzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ofengx.cloudserverzuul.bean.JwtTokeInfo;
import com.ofengx.cloudserverzuul.remote.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {

    @Autowired
    private SecurityService securityService;

    private String prefix = "Bearer";

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestHeader = request.getHeader(this.tokenHeader);

        String authToken = null;
        if (requestHeader != null
                && requestHeader.startsWith(this.prefix)) {
            authToken = requestHeader.substring(7);
            JwtTokeInfo jwtTokeInfo = securityService.verification(authToken);
        }

//             不对请求进行路由
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(400);
//        ctx.set("isSuccess", false);
//        ctx.setResponseBody("400");

        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
