package com.example.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.example.model.ProducerAwardInterval;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmeIntegracaoTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetMaxIntervalProducer() {
        ResponseEntity<ProducerAwardInterval> response = restTemplate
                .getForEntity("/api/max-interval-producer", ProducerAwardInterval.class);
        // Asserções para verificar os dados
    }

    @Test
    public void testGetFastestAwardProducer() {
        ResponseEntity<ProducerAwardInterval> response = restTemplate
                .getForEntity("/api/fastest-award-producer", ProducerAwardInterval.class);
        // Asserções para verificar os dados
    }
}
