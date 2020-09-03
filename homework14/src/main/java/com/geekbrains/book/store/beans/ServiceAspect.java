package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.MethodStats;
import com.geekbrains.book.store.services.MethodStatsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    @Autowired
    private MethodStatsService methodStatService;

    @Before("execution(public * com.geekbrains.book.store.services.BookService.*(..))")
    public void beforeAnyMethodInBookService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStats methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
    }

    @Before("execution(public * com.geekbrains.book.store.services.UserService.*(..))")
    public void beforeAnyMethodInUserService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStats methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
    }

    @Before("execution(public * com.geekbrains.book.store.services.OrderService.*(..))")
    public void beforeAnyMethodInOrderService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStats methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
    }
}
