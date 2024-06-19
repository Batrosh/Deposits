package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import com.deposit.model.Apps;
import com.deposit.model.enums.AppsStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apps")
public class AppsCont extends Attributes {

    @GetMapping
    public String apps(Model model) {
        AddAttributesApps(model);
        return "apps";
    }

    @GetMapping("/client/{id}")
    public String apps(Model model, @PathVariable Long id) {
        getCurrentUserAndRole(model);
        model.addAttribute("client", usersRepo.getReferenceById(id));
        return "credits_history";
    }

    @GetMapping("/conf/{id}")
    public String appConfirmed(@PathVariable Long id) {
        Apps app = appsRepo.getReferenceById(id);
        app.setStatus(AppsStatus.CONFIRMED);
        appsRepo.save(app);
        return "redirect:/apps";
    }

    @PostMapping("/reject/{id}")
    public String appReject(@PathVariable Long id, @RequestParam String reason) {
        Apps app = appsRepo.getReferenceById(id);
        app.setStatus(AppsStatus.REJECTED);
        app.setReason(reason);
        appsRepo.save(app);
        return "redirect:/apps";
    }


}
