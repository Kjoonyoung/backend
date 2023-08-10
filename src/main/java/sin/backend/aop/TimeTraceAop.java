package sin.backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {
    //@Around("execution(* sin.backend..*(..))") //backend 모든 것
    @Around("execution(* sin.backend.service..*(..))") //backend 서비스 메소드 패키지 .. 모든것
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+start);
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end-start;
            System.out.println("##걸린시간: "+ joinPoint.toString() + " "+ timeMs+"ms");
        }
    }
}
