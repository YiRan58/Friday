package awesome.friday.dao.kafka;

import awesome.friday.dao.entity.Log;
import awesome.friday.dao.service.ILogService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yiran
 * @date: 20.11.1 18:59
 */
@Component
public class LogToBase {

    @Autowired
    ILogService logService;

    @KafkaListener(topics = "log")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMassage.isPresent()){
            String x = (String)kafkaMassage.get();
            Log log = JSON.parseObject(x,Log.class);

            logService.save(log);
        }
    }
}
