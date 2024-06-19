package com.deposit.controller.main;

import com.deposit.model.enums.AppsStatus;
import com.deposit.model.enums.Goals;
import com.deposit.model.enums.Role;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void getCurrentUserAndRole(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesUsers(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesCredits(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("credits", creditsRepo.findAll());
        model.addAttribute("goals", Goals.values());
    }

    protected void AddAttributesCredit(Model model, Long id) {
        getCurrentUserAndRole(model);
        model.addAttribute("credit", creditsRepo.getReferenceById(id));
    }

    protected void AddAttributesApps(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("apps", appsRepo.findAllByStatus(AppsStatus.WAITING));
    }

    protected void AddAttributesCreditsMy(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("apps", getUser().getAppsList());
    }

    protected void AddAttributesClients(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("clients", usersRepo.findAll());
    }

    protected void AddAttributesCreditsSearch(Model model, Goals goal) {
        getCurrentUserAndRole(model);
        model.addAttribute("credits", creditsRepo.findAllByGoal(goal));
        model.addAttribute("goals", Goals.values());
        model.addAttribute("selectedG", goal);
    }

    protected void AddAttributesCreditAdd(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("goals", Goals.values());
    }

    protected void AddAttributesCreditEdit(Model model,Long id) {
        getCurrentUserAndRole(model);
        model.addAttribute("goals", Goals.values());
        model.addAttribute("credit", creditsRepo.getReferenceById(id));
    }
}
