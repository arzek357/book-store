package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.ServicesMethodCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private ServicesMethodCounter servicesMethodCounter;

    @Autowired
    public void setServicesMethodCounter(ServicesMethodCounter servicesMethodCounter) {
        this.servicesMethodCounter = servicesMethodCounter;
    }

    @GetMapping
    public String showHomePage() {
        return "about-page";
    }

    @GetMapping("/servicesCount")
    public String showServicesCountPage(Model model){
        model.addAttribute("methods",servicesMethodCounter.getMethodNumber());
        return "services-count-page";
    }
}
