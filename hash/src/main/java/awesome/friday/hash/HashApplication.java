package awesome.friday.hash;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDubboConfiguration
public class HashApplication {

    public static void main(String[] args) {

        SpringApplication.run(HashApplication.class, args);
    }

}
