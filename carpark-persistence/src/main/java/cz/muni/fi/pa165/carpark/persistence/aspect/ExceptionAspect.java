package cz.muni.fi.pa165.carpark.persistence.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * Created by karelfajkus on 06/11/2016.
 */
@Aspect
@Component
public class ExceptionAspect {

    @AfterThrowing(pointcut="execution(* cz.muni.fi.pa165.carpark.persistence..*.*(..))",
            throwing="ex")
    public void DataAccessExceptionReturn(Exception ex) {
        throw new DataAccessException(ex.getMessage()) {};
    }
}
