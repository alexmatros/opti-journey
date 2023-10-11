package com.matros.optijourney.controllers;

import com.matros.optijourney.repositories.JourneyData;
import com.matros.optijourney.repositories.JourneyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JourneyDataRepository journeyDataRepository;

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
            Model model) {

        JourneyData journeyData = new JourneyData();
        journeyData.setOrigin(origin);
        journeyData.setDestination(destination);
        journeyData.setWaypoints(waypoints);

        journeyDataRepository.save(journeyData);

        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("waypoints", waypoints);

        return "redirect:/map";
    }
}
