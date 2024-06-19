package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calc")
public class CalcCont extends Attributes {
    @GetMapping
    public String calc(Model model) {
        getCurrentUserAndRole(model);
        return "calc";
    }

    @GetMapping("/calc")
    public String calc(Model model, @RequestParam int rate, @RequestParam int sum, @RequestParam int term) {
        getCurrentUserAndRole(model);

        model.addAttribute("rate", rate);
        model.addAttribute("sum", sum);
        model.addAttribute("term", term);

        model.addAttribute("result", (rate * term * sum / 12) / 100);   

        return "calc";
    }
}
