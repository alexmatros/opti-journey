package com.matros.optijourney.controllers;

import com.matros.optijourney.repositories.JourneyData;
import com.matros.optijourney.services.JourneyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapsController {

    @Value("${googlemaps.apikey}")
    private String apiKey;

    @Autowired
    JourneyDataService journeyDataService;

    @GetMapping("/map")
    public String mapPage(Model model) {
        model.addAttribute("apiKey", apiKey);

        JourneyData journeyData = journeyDataService.loadJourneyData();
        model.addAttribute("origin", journeyData.getOrigin());
        model.addAttribute("waypoints", journeyData.getWaypoints());
        model.addAttribute("destination", journeyData.getDestination());
        model.addAttribute("mode", journeyData.getMode());

        return "map";
    }
}
