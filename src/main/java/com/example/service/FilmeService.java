package com.example.service;

import com.example.model.AwardInterval;
import com.example.model.AwardIntervalResponse;
import com.example.model.ProducerAwardInterval;
import com.example.repository.FilmeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Filme;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Author: Coutinho
 */
@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

 /*   public ProducerAwardInterval getProducerAwardInterval() {
        // Lógica para obter o produtor com maior intervalo entre prêmios consecutivos
        return null;
    }*/

    public ProducerAwardInterval getProducerFastestAward() {
        // Lógica para obter o produtor que ganhou dois prêmios mais rápido
        return null;

    }
    @PostConstruct
    public void loadFilms() {
        String filePath = "src/main/resources/Movielist.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            List<Filme> films = rows.stream().skip(1) // Pular a linha do cabeçalho
                    .map(row -> {
                        Filme film = new Filme();
                        film.setTitle(row[0]);
                        film.setProducer(row[1]);
                        film.setYear(Integer.parseInt(row[2]));
                        film.setWinner("yes".equalsIgnoreCase(row[3]));
                        return film;
                    }).collect(Collectors.toList());

            filmeRepository.saveAll(films);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public AwardIntervalResponse getProducerAwardIntervals() {
        List<Filme> winners = filmeRepository.findAll().stream()
                .filter(Filme::isWinner)
                .sorted(Comparator.comparing(Filme::getYear))
                .collect(Collectors.toList());

        Map<String, List<Integer>> producerWins = new HashMap<>();
        for (Filme film : winners) {
            for (String producer : film.getProducer().split(", ")) {
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(film.getYear());
            }
        }

        List<AwardInterval> minIntervals = new ArrayList<>();
        List<AwardInterval> maxIntervals = new ArrayList<>();

        int maxInterval = 0;
        int minInterval = Integer.MAX_VALUE;

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                AwardInterval awardInterval = new AwardInterval();
                awardInterval.setProducer(entry.getKey());
                awardInterval.setInterval(interval);
                awardInterval.setPreviousWin(years.get(i - 1));
                awardInterval.setFollowingWin(years.get(i));

                if (interval > maxInterval) {
                    maxIntervals.clear();
                    maxIntervals.add(awardInterval);
                    maxInterval = interval;
                } else if (interval == maxInterval) {
                    maxIntervals.add(awardInterval);
                }

                if (interval < minInterval) {
                    minIntervals.clear();
                    minIntervals.add(awardInterval);
                    minInterval = interval;
                } else if (interval == minInterval) {
                    minIntervals.add(awardInterval);
                }
            }
        }

        AwardIntervalResponse response = new AwardIntervalResponse();
        response.setMin(minIntervals);
        response.setMax(maxIntervals);
        return response;
    }
   /*public ProducerAwardInterval getProducerFastestAward() {
        List<Filme> winners = filmeRepository.findAll().stream()
                .filter(Filme::isWinner)
                .sorted(Comparator.comparing(Filme::getYear))
                .collect(Collectors.toList());

        Map<String, List<Integer>> producerWins = new HashMap<>();
        for (Filme film : winners) {
            for (String producer : film.getProducer().split(", ")) {
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(film.getYear());
            }
        }

        String producerWithFastestAward = null;
        int fastestInterval = Integer.MAX_VALUE;

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                if (interval < fastestInterval) {
                    fastestInterval = interval;
                    producerWithFastestAward = entry.getKey();
                }
            }
        }

        ProducerAwardInterval result = new ProducerAwardInterval();
        result.setProducer(producerWithFastestAward);
        result.setInterval(fastestInterval);
        return result;
    }*/

}
