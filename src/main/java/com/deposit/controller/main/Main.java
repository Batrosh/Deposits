package com.deposit.controller.main;

import com.deposit.model.Users;
import com.deposit.repo.AppsRepo;
import com.deposit.repo.CreditsRepo;
import com.deposit.repo.UserDescriptionsRepo;
import com.deposit.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class Main {

    @Autowired
    protected AppsRepo appsRepo;
    @Autowired
    protected CreditsRepo creditsRepo;
    @Autowired
    protected UserDescriptionsRepo userDescriptionsRepo;
    @Autowired
    protected UsersRepo usersRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    public static String getDate() {
        return LocalDateTime.now().toString().substring(0, 10);
    }

}