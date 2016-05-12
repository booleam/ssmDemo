package com.zxs.ssm.aop;

import com.alibaba.fastjson.JSON;
import com.zxs.ssm.model.ResponseDO;
import com.zxs.ssm.utils.LoggerUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by asen on 16/5/12.
 */
@Aspect
@Component
public class LogAspect {

//    @Around(value = "execution(* com.zxs.ssm.controller.*.*(..))")
//    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        LoggerUtil.logger.info("around advice");
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
//        LoggerUtil.logger.info("各个参数, url: " + url + ", method: " + method + ", uri: " + uri + ", params: " + queryString);
//
//        Object proceed = proceedingJoinPoint.proceed();
//        if(proceed == null) {
//            LoggerUtil.logger.info("result is null");
//        } else {
//            LoggerUtil.logger.info("result type: " + proceed.getClass());
//            if (proceed instanceof ResponseDO) {
//                ResponseDO result = (ResponseDO) proceed;
//                LoggerUtil.logger.info("resultCode: " + result.getResultCode());
//                LoggerUtil.logger.info("data: " + JSON.toJSONString(result));
//            }
//        }
//
//        return proceed;
//    }

    @AfterReturning(pointcut = "execution(* com.zxs.ssm.controller.*.*(..))", returning = "object")
    public void log(Object object) {
        if(object == null) {
            LoggerUtil.logger.info("result is null");
        } else {
            LoggerUtil.logger.info("result type: " + object.getClass());
            if (object instanceof ResponseDO) {
                ResponseDO result = (ResponseDO) object;
                LoggerUtil.logger.info("resultCode: " + result.getResultCode());
                LoggerUtil.logger.info("data: " + JSON.toJSONString(result));
            }
            if(object instanceof String) {
                LoggerUtil.logger.info("String: " + object.toString());
            }
        }
    }
}
