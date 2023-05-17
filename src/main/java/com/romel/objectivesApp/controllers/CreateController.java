package com.romel.objectivesApp.controllers;

import com.romel.objectivesApp.models.Objective;
import com.romel.objectivesApp.services.ObjectiveServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CreateController {

    @Autowired
    private ObjectiveServices services;

    @GetMapping("/create-objective")
    public String showCreate (Objective objective) {
        return "new-objective";
    }

    @PostMapping("/do")
    public String createObjective(@Valid Objective objective, BindingResult result, Model model) {

        Objective objective1 = new Objective();
        objective1.setDescription(objective.getDescription());
        objective1.setIsComplete(objective.getIsComplete());

        services.save(objective);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteObjective(@PathVariable("id") Long id, Model model){
        Objective objective = services.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Objective id: " + id + " not found"));

        services.delete(objective);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editObjective(@PathVariable ("id") Long id, Model model){
        Objective objective = services.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Objective id: " + id + " not found"));


        model.addAttribute("do", objective);
        return "edit-objective";
    }

    @PostMapping("/do/{id}")
    public String updateObjective(@PathVariable("id") Long id, @Valid Objective objective, BindingResult result, Model model){
        Objective objective1 = services.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Objective id: " + id + " not found"));

        objective1.setIsComplete(objective.getIsComplete());
        objective1.setDescription(objective.getDescription());

        services.save(objective1);

        return "redirect:/";
    }
}
