package io.khusanjon.onlineclassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"io.khusanjon.onlineclassroom.model.domain"})
@EnableJpaRepositories(basePackages = {"io.khusanjon.onlineclassroom.repository"})
public class OnlineClassroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineClassroomApplication.class, args);
    }
}
