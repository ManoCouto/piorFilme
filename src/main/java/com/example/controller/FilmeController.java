package com.example.controller;

import com.example.model.AwardIntervalResponse;
import com.example.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.ProducerAwardInterval;
/**
 * Author: Coutinho
 */
@RestController
@RequestMapping("/api")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/max-interval-producer")
    public AwardIntervalResponse getAwardIntervals() {
        return filmeService.getProducerAwardIntervals();
    }

    @GetMapping("/fastest-award-producer")
    public ProducerAwardInterval getFastestAwardProducer() {
        return filmeService.getProducerFastestAward();
    }
}
