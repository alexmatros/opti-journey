package com.matros.optijourney.services;

import com.matros.optijourney.repositories.JourneyData;
import com.matros.optijourney.repositories.JourneyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyDataService {
    private final JourneyDataRepository journeyDataRepository;

    @Autowired
    public JourneyDataService(JourneyDataRepository journeyDataRepository) {
        this.journeyDataRepository = journeyDataRepository;
    }

    public void saveJourneyData(JourneyData journeyData) {
        journeyDataRepository.save(journeyData);
    }

    public JourneyData loadJourneyData() {
        return journeyDataRepository.findAll().get(0);
    }

    public void clearDatabase() {
        journeyDataRepository.deleteAll();
    }
}
