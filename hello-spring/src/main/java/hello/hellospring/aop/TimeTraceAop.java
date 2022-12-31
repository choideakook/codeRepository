package hello.hellospring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Objects execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start = " + joinPoint.toString());
        try {
            return (Objects) joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            Long timeMs = finish - start;
            System.out.println(
                    "End = " + joinPoint.toString() + " " + timeMs + "ms"
            );
        }
    }
}
