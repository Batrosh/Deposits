package com.deposit.controller;

import com.deposit.controller.main.Attributes;
import com.deposit.model.Apps;
import com.deposit.model.Credits;
import com.deposit.model.enums.Goals;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditsCont extends Attributes {

    @GetMapping
    public String credits(Model model) {
        AddAttributesCredits(model);
        return "credits";
    }

    @GetMapping("/{id}")
    public String credit(Model model, @PathVariable Long id) {
        AddAttributesCredit(model, id);
        return "credit";
    }

    @GetMapping("/my")
    public String creditsMy(Model model) {
        AddAttributesCreditsMy(model);
        return "credits_my";
    }

    @PostMapping("/form/{id}")
    public String creditForm(@PathVariable Long id, @RequestParam int sum) {
        appsRepo.save(new Apps(creditsRepo.getReferenceById(id), getUser(), sum));
        return "redirect:/credits/{id}";
    }

    @PostMapping("/search")
    public String credits(Model model, @RequestParam Goals goal) {
        AddAttributesCreditsSearch(model, goal);
        return "credits";
    }

    @GetMapping("/add")
    public String creditAdd(Model model) {
        AddAttributesCreditAdd(model);
        return "credit_add";
    }

    @PostMapping("/add")
    public String creditAdd(Model model, @RequestParam String name, @RequestParam String payments, @RequestParam int rate, @RequestParam int term, @RequestParam int minLimit, @RequestParam int maxLimit, @RequestParam Goals goal, @RequestParam MultipartFile file, @RequestParam String description) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "credits/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesCreditAdd(model);
                return "credit_add";
            }
        }

        Credits credit = creditsRepo.save(new Credits(name, rate, term, minLimit, maxLimit, goal, res, description, payments));
        return "redirect:/credits/" + credit.getId();
    }

    @GetMapping("/edit/{id}")
    public String creditEdit(Model model, @PathVariable Long id) {
        AddAttributesCreditEdit(model, id);
        return "credit_edit";
    }

    @PostMapping("/{id}/edit")
    public String creditEdit(Model model, @RequestParam String name, @RequestParam String payments, @RequestParam int rate, @RequestParam int term, @RequestParam int minLimit, @RequestParam int maxLimit, @RequestParam Goals goal, @RequestParam MultipartFile file, @RequestParam String description, @PathVariable Long id) {
        Credits credit = creditsRepo.getReferenceById(id);
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    String res = "credits/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                    credit.setFile(res);
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesCreditEdit(model, id);
                return "credit_add";
            }
        }

        credit.set(name, rate, term, minLimit, maxLimit, goal, description, payments);
        creditsRepo.save(credit);
        return "redirect:/credits/{id}";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        creditsRepo.deleteById(id);
        return "redirect:/credits";
    }
}
