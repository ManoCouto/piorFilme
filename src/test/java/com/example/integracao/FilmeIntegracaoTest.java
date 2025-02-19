package com.example.integration;

import com.example.GoldenRaspberryAwardsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmeIntegracaoTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAwardIntervals() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/award-intervals", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
