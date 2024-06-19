package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Attributes {
    @GetMapping
    public String index1() {
        return "redirect:/credits";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/credits";
    }
}
