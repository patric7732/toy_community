package org.example.toy_restboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ToyRestboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyRestboardApplication.class, args);
    }

}
