package com.example.mySource.architecture;

import com.example.mySource.abstractclass.AbstractComponent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAspect extends AbstractComponent {

    @Around("execution(* *..*Controller.*(..))")
    public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
        Object ret;
        try {
            log.debug("★★★Before by @Around         : " + jp.getSignature());
            ret = jp.proceed();
            log.debug("★★★AfterReturning by @Around : " + jp.getSignature() + " ret: " + ret);
        }catch (Throwable t) {
            log.debug("★★★AfterThrowing by @Around  : " + jp.getSignature() + " t: " + t);
            throw t;
        } finally {
            log.debug("★★★After by @Around          : " + jp.getSignature());
        }
        return ret;
    }
}
