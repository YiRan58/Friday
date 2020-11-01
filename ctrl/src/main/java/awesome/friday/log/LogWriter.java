package awesome.friday.log;

import java.lang.annotation.*;

/**
 * @author yiran
 * @date: 20.11.1 17:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogWriter {
    String value() default "";
}
