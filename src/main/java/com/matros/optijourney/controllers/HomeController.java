package com.matros.optijourney.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("numFields", 0);
        return "homepage";
    }

    @PostMapping("/submit")
    public String handleSubmit(@RequestParam List<String> textFields) {
        // Process the form submission here
        // textFields will contain a list of all the text field values
        return "redirect:/map"; // Redirect back to the homepage
    }
}
