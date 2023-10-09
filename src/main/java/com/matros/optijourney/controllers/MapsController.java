package com.matros.optijourney.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @Value("${googlemaps.apikey}")
    private String apiKey;

    @GetMapping("/map")
    public String mapPage(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "map";
    }
}
