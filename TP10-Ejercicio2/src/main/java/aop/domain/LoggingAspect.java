package aop.domain;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before(value = "execution(* aop.domain..*(..))")
    public void beforeAdvice(JoinPoint point) {
	System.out.println("before calling methods...");
    }

    @After(value = "execution(* aop.domain.DefaultUsers.userById*(..))")
    public void afterAdvice(JoinPoint point) {
	var param = point.getArgs()[0];
	System.out.println("filtro de busqueda: " + param);
    }

    @AfterThrowing(value = "execution(* aop.domain..*(..))", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
	System.out.println("after throwing: " + error.getMessage());
    }

    @Around("@annotation(Trace)")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
	System.out.println("around before...");

	var value = point.proceed();

	System.out.println("around after...");

	return value;
    }

    @Around("@annotation(Time)")
    public Double aroundAddUsers(ProceedingJoinPoint point) throws Throwable {

	Long inicio = System.currentTimeMillis();

	point.proceed();

	Long fin = System.currentTimeMillis();

	Long total = fin - inicio;
	Double totalEnSegundos = total / 1000.0;

	System.out.println(totalEnSegundos.toString());

	return totalEnSegundos;
    }
}
