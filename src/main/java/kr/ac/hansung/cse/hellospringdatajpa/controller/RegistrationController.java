package kr.ac.hansung.cse.hellospringdatajpa.controller;

import java.util.ArrayList;
import java.util.List;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyRole;
import kr.ac.hansung.cse.hellospringdatajpa.entity.MyUser;
import kr.ac.hansung.cse.hellospringdatajpa.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/signup")
    public String showRegistrationPage(Model model) {
        MyUser user = new MyUser();
        model.addAttribute("user", user);

        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") MyUser user, Model model) {
        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }

        List<MyRole> userRoles = new ArrayList<>();
        userRoles.add(registrationService.findRoleByRoleName("ROLE_USER"));

        if ("admin@hansung.ac.kr".equals(user.getEmail())) {
            userRoles.add(registrationService.findRoleByRoleName("ROLE_ADMIN"));
        }

        registrationService.createUser(user, userRoles);

        return "redirect:/products";
    }
}
