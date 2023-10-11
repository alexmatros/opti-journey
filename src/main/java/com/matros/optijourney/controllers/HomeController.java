package com.matros.optijourney.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Value("${googlemaps.apikey}")
    private String apiKey;

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("numFields", 0);
        model.addAttribute("apiKey", apiKey);
        return "homepage";
    }

    @PostMapping("/submit")
    public String submitForm(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("waypoints") List<String> waypoints,
            Model model) {

        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Waypoints: " + waypoints);

        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("waypoints", waypoints);

        return "redirect:/map";
    }
}
