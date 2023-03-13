package app.just.actionproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ActionProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActionProducerApplication.class, args);
    }

}
