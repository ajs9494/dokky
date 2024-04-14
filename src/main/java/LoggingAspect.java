import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logBeforeTransactionStart() {
        logger.info("Transaction started");
    }

    @AfterReturning("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logAfterTransactionSuccessful() {
        logger.info("Transaction successfully committed");
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
    public void logAfterTransactionException(Throwable ex) {
        logger.error("Transaction failed with exception: {}", ex.getMessage());
    }
}
