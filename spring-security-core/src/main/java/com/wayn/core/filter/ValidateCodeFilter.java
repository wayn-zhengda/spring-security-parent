package com.wayn.core.filter;

import com.wayn.core.constant.ValidateCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("执行验证码校验");
        if (StringUtils.equals("/form/login", request.getRequestURI()) && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")){
            boolean b = validateCode(new ServletWebRequest(request));
        }
    }

    private boolean validateCode(ServletWebRequest servletWebRequest) {
        String sessionCode = (String) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeConstant.IMAGE_SESSION_KEY);
        String matchCode = (String) servletWebRequest.getAttribute("code", 0);
        return sessionCode.equals(matchCode);
    }
}
