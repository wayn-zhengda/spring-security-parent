package com.wayn.core.filter;

import com.wayn.core.constant.ValidateCodeConstant;
import com.wayn.core.exception.ValidataCodeException;
import com.wayn.core.validate.code.image.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (StringUtils.equals("/form/login", request.getRequestURI()) && StringUtils.endsWithIgnoreCase(request.getMethod(), "post")){
            log.info("执行验证码校验");
            try {
                validateCode(new ServletWebRequest(request));
            }catch (ValidataCodeException e){
                log.error("验证码错误, {}", request.getRequestURL());
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean validateCode(ServletWebRequest servletWebRequest) {
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeConstant.IMAGE_SESSION_KEY);
        String matchCode = (String) servletWebRequest.getAttribute("code", 0);
        if (imageCode.getCode().equals(matchCode)){
            throw new ValidataCodeException("验证码错误");
        }
        return true;
    }
}
