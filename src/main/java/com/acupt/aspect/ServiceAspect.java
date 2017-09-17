package com.acupt.aspect;

import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 * Created by liujie on 2017/8/30.
 */
@Aspect
@Component
public class ServiceAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(com.acupt.domain.Result com.acupt.service..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Throwable t = null;
        try {
            return pjp.proceed();
        } catch (DataIntegrityViolationException e) {
            t = e;
            return new Result<>(CodeEnum.RECORD_DUPLICATE);
        } catch (Throwable e) {
            t = e;
            return new Result<>(CodeEnum.SYS_ERR);
        } finally {
            if (t != null) {
                logger.error(t.getClass().getSimpleName() + ":" + t.getMessage(), t);
            }
        }
    }
}
