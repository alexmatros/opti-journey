package com.matros.optijourney.controllers;

import com.matros.optijourney.components.JourneyOptimizer;
import com.matros.optijourney.repositories.JourneyData;
import com.matros.optijourney.repositories.JourneyDataRepository;
import com.matros.optijourney.services.GoogleMapsService;
import com.matros.optijourney.services.JourneyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JourneyOptimizer journeyOptimizer;

    @Autowired
    private JourneyDataService journeyDataService;

    @Value("${googlemaps.apikey}")
    private String apiKey;

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("numFields", 1);
        model.addAttribute("apiKey", apiKey);
        return "homepage";
    }

    @PostMapping("/submit")
    public String submitForm(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam(value = "waypoints", required = false) List<String> waypoints,
            @RequestParam("toggle") String metric,
            @RequestParam("mode") String mode) {

        mode = mode.toUpperCase();
        System.out.println(mode);

        for (String w : waypoints) { System.out.println(w); }

        List<String> journey = journeyOptimizer.optimizeJourney(origin, waypoints, destination, metric, mode);
        List<String> orderedWaypoints = journey.subList(1, journey.size() - 1);

        journeyDataService.clearDatabase();
        JourneyData journeyData = new JourneyData(origin, orderedWaypoints, destination, metric, mode);
        journeyDataService.saveJourneyData(journeyData);

        return "redirect:/map";
    }
}
