package ml.mjuchallengers.ex7_mreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex7MreviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ex7MreviewApplication.class, args);
    }

}
