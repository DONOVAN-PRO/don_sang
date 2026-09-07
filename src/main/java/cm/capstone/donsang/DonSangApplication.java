package cm.capstone.donsang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DonSangApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonSangApplication.class, args);
    }
}
