package com.romel.objectivesApp.controllers;

import com.romel.objectivesApp.services.ObjectiveServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjectiveController {
    @Autowired
    private ObjectiveServices services;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("Objectives", services.getAll());
        return modelAndView;
    }
}
