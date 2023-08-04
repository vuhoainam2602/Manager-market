package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project_mart.model.PointCard;
import project_mart.model.Supplier;
import project_mart.model.User;
import project_mart.service.PointCardService;
import project_mart.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
public class PointCardController {
    @Autowired
    private PointCardService pointCardService;

    @Autowired
    private UserService userService;

    @GetMapping("/list-card")
    public String index(Model model){
        model.addAttribute("card", pointCardService.findAll());
        return "card/list_card";
    }

    @GetMapping(value = {"/add-card"})
    public String addCard(Model model) {
        model.addAttribute("card", new PointCard());
        model.addAttribute("user", userService.findAll());
        return "card/add_card";
    }

    @PostMapping("/add-card")
    public String addCard(@ModelAttribute("card") PointCard pointCard){
            System.out.println(pointCard);
            pointCardService.add(pointCard);
            return "redirect:/list-card";

    }

    @GetMapping("/edit-card/{id}")
    public String editCard(Model model, @PathVariable Long id) {
        PointCard pointCard = pointCardService.findByID(id);
        System.out.println(pointCard);
        model.addAttribute("card", pointCard);
        model.addAttribute("user", userService.findAll());
        return "card/edit_card";
    }

    @PostMapping("/update-card")
    public String editCard(@ModelAttribute("card") PointCard pointCard) {
        System.out.println(pointCard);
        pointCardService.update(pointCard);
//        model.addAttribute("success", "Thêm nhà cung cấp thành công");
        return "redirect:/list-card";// Trả về failed nếu không thành công
    }

    @GetMapping("/delete-card/{id}")
    public String deleteCard(@PathVariable("id") Long id) {
        pointCardService.deleteById(id);
        return "redirect:/list-card";
    }

    @GetMapping("/search-card")
    public String searchCard(String search, Model model){
        model.addAttribute("card", pointCardService.search(search));
        return "card/list_card";
    }
}
