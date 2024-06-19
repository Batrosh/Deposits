package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientsCont extends Attributes {

    @GetMapping
    public String clients(Model model) {
        AddAttributesClients(model);
        return "clients";
    }
}
