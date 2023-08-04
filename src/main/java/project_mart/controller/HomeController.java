package project_mart.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/"})
    public String homepage() {
        return "welcome";
    }

    @GetMapping("/home")
    public String hello() {
        return "home";
    }
}
