package com.example.mbtiboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MbtiBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtiBoardApplication.class, args);
    }

}
