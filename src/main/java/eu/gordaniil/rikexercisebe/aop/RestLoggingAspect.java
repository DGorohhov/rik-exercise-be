package eu.gordaniil.rikexercisebe.aop;

import eu.gordaniil.rikexercisebe.helper.CorrelationIdHelper;
import eu.gordaniil.rikexercisebe.log.LogEntry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

@Aspect
@Slf4j
@Configuration
public class RestLoggingAspect {

    private static final String X_REQUEST_ID = "X-Request-ID";

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        var methodName = joinPoint.getSignature().toShortString();
        var logEntry = getNewData();

        log.error("EXCEPTION: {} : {} Error({})", methodName, logEntry, e.getMessage());
    }

    @Before("springBeanPointcut()")
    public void logBeforeCall(JoinPoint joinPoint) {
        var entry = getNewData();
        var methodName = joinPoint.getSignature().toShortString();

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> INCOMING REQUEST: {}, {}", methodName, entry);
    }

    @After("springBeanPointcut()")
    public void logAfterCall(JoinPoint joinPoint) {
        var entry = getNewData();
        var methodName = joinPoint.getSignature().toShortString();

        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<< OUTGOING RESPONSE: {}, {}", methodName, entry);
    }

    private LogEntry getNewData() {
        var request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        var xRequestId = Optional.ofNullable(request.getHeader(X_REQUEST_ID));

        return LogEntry.builder()
                .ip(request.getRemoteHost())
                .correlationId(CorrelationIdHelper.correlationId())
                .url(request.getRequestURI())
                .xRequestId(xRequestId.orElse("-"))
                .build();
    }

}

