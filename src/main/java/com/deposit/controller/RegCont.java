package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import com.deposit.model.Users;
import com.deposit.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {

    @GetMapping
    public String reg(Model model) {
        getCurrentUserAndRole(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String fio, @RequestParam String passport) {
        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            getCurrentUserAndRole(model);
            return "reg";
        }

        Users user = new Users(username, password,fio,passport);

        if (usersRepo.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
            user.setEnabled(true);
        }

        usersRepo.save(user);

        return "redirect:/login";
    }
}
