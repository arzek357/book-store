package com.geekbrains.book.store.beans;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Aspect
@Data
@Component
public class ServicesMethodCounter {
    private HashMap<String,Integer> methodNumber;

    @PostConstruct
    public void init(){
        methodNumber = new HashMap<>();
    }


    @After("execution(public * com.geekbrains.book.store.services.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();
        if (!methodNumber.containsKey(name)){
            methodNumber.put(name,1);
        }
        else{
            methodNumber.put(joinPoint.getTarget().getClass().getName(),methodNumber.get(name)+1);
        }
    }
}
