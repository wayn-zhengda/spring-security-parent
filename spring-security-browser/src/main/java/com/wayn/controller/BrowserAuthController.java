package com.wayn.controller;


import com.wayn.core.common.R;
import com.wayn.core.properties.WaynSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping
public class BrowserAuthController {

    private HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private WaynSecurityProperties waynSecurityProperties;


    @RequestMapping("/require/auth")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public R requireAuthentication(HttpServletRequest request, HttpServletResponse response){
        SavedRequest cacheResquest = httpSessionRequestCache.getRequest(request, response);
        if (cacheResquest != null){
            String redirectUrl = cacheResquest.getRedirectUrl();
            if (StringUtils.isNoneEmpty(redirectUrl) && redirectUrl.endsWith(".html")){
                try {
                    log.info("{}引发跳转", redirectUrl);
                    redirectStrategy.sendRedirect(request, response, waynSecurityProperties.getBrowser().getSignPage());
                } catch (IOException e) {
                    log.error("跳转登陆页异常, {} ,异常信息:{}", request.getRequestURL(), e.getMessage());
                }
            }
        }
        return R.ok("需要登陆后操作");
    }
}
