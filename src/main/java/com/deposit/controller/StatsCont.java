package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import com.deposit.model.Credits;
import com.deposit.model.enums.AppsStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Attributes {
    @GetMapping
    public String stats(Model model) {
        getCurrentUserAndRole(model);

        AppsStatus[] appsStatuses = AppsStatus.values();

        String[] appsStatusesNames = new String[appsStatuses.length];
        int[] appsStatusesValues = new int[appsStatuses.length];

        for (int i = 0; i < appsStatuses.length; i++) {
            appsStatusesNames[i] = appsStatuses[i].getName();
            appsStatusesValues[i] = appsRepo.findAllByStatus(appsStatuses[i]).size();
        }

        model.addAttribute("appsStatusesNames", appsStatusesNames);
        model.addAttribute("appsStatusesValues", appsStatusesValues);

        List<Credits> credits = creditsRepo.findAll();

        credits.sort(Comparator.comparing(Credits::getAppsSize));
        Collections.reverse(credits);

        String[] creditsNames = new String[5];
        Arrays.fill(creditsNames, "");
        int[] creditsValues = new int[5];

        for (int i = 0; i < 5; i++) {
            creditsNames[i] = credits.get(i).getName();
            creditsValues[i] = credits.get(i).getApps().size();
        }

        model.addAttribute("creditsNames", creditsNames);
        model.addAttribute("creditsValues", creditsValues);

        return "stats";
    }
}
