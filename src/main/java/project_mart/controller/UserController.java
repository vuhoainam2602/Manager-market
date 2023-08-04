package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project_mart.model.*;
import project_mart.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    //Danh sách bill
    @GetMapping("/list-user")
    public String index(Model model) {
        model.addAttribute("list", userService.findAll());
        return "user/list_user";
    }

    @GetMapping("/search-user")
    public String searchPro(String search, Model model) {
        model.addAttribute("list", userService.search(search));
        return "user/list_user";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/list-user";
    }

    @GetMapping("/add-user")
    public String addImport(Model model) {

        model.addAttribute("user", new User());
        return "user/add_user";
    }

    @PostMapping("/add-user")
    public String addBillImport(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); //mã hóa
        userService.add(user); // thêm db
        return "redirect:/list-user";
    }

    @GetMapping("/edit-user/{id}")
    public String editSupplier(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "user/edit_user";
    }

    @PostMapping("/update-user")
    public String editSupplier(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/list-user";
    }
}
