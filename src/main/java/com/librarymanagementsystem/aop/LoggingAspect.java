package com.librarymanagementsystem.aop;


import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Before("within(com.librarymanagementsystem.ctrl.*)")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("IP Address: " + getIpAddress() + " - Entering in Method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("within(com.librarymanagementsystem.ctrl.*)")
    public void logAfterReturning(JoinPoint joinPoint) {
        LOGGER.info("IP Address: " + getIpAddress() + " - Returning from Method: " + joinPoint.getSignature().getName());
    }

    private String getIpAddress(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ipAddress = null;
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();
            ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }
        }
        return ipAddress;
    }
}
