package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import com.deposit.model.Users;
import com.deposit.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Attributes {
    @GetMapping
    public String subs(Model model) {
        AddAttributesUsers(model);
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(role);
        usersRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersRepo.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/enable")
    public String enable(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setEnabled(true);
        usersRepo.save(user);
        return "redirect:/users";
    }
}
