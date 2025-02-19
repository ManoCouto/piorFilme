package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.service.FilmeService;
/**
 * Author: Coutinho
 */
@SpringBootApplication
public class GoldenRaspberryAwardsApplication implements CommandLineRunner {

    @Autowired
    private final FilmeService filmeService;

    public GoldenRaspberryAwardsApplication(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        filmeService.loadFilms();
    }
}
