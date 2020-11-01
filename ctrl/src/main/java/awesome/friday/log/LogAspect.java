package awesome.friday.log;

import awesome.friday.Utils.HttpContextUtils;
import awesome.friday.Utils.IPUtil;
import awesome.friday.ctrl.entity.Log;
import awesome.friday.dto.R;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yiran
 * @date: 20.11.1 17:02
 */
@Aspect
@Component
public class LogAspect {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Pointcut("@annotation(awesome.friday.log.LogWriter)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint  point) throws Throwable {
        Object r = point.proceed();
        log(point);
        return r;
    }

    private void log(ProceedingJoinPoint  joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Log log = new Log();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethodName(className + "." + methodName + "()");

        Object[] args = joinPoint.getArgs();
        String params = "null";
        try {
            if (args.length != 0) {
                params = JSON.toJSONString(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.setParam(params);
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IPUtil.getIpAddr(request));
        log.setOperationTime(new Date());

        kafkaTemplate.send("log", JSON.toJSONString(log));

    }

}
