package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project_mart.model.Supplier;
import project_mart.service.SupplierService;

import java.io.IOException;


@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list-supplier")
    public String index(Model model){
        model.addAttribute("list",supplierService.findAll());
        return "supplier/sup_list";
    }

    @GetMapping(value = {"/add-supplier"})
    public String addSupplier(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/sup_add";
    }

    @PostMapping("/add-supplier")
    public String addSupplier(@ModelAttribute("supplier") Supplier supplier) throws IllegalStateException, IOException {
        System.out.println("Success");
        supplierService.add(supplier);
//        model.addAttribute("success", "Thêm nhà cung cấp thành công");
        return "redirect:/list-supplier";// Trả về failed nếu không thành công
    }

    @GetMapping("/edit-supplier/{id}")
    public String editSupplier(Model model, @PathVariable Long id) {
        Supplier supplier = supplierService.findByID(id);
        System.out.println(supplier);
        model.addAttribute("supplier", supplier);
        return "supplier/sup_edit";
    }

    @PostMapping("/update-supplier")
    public String editSupplier(@ModelAttribute("supplier") Supplier supplier) {
        System.out.println("Success");
        supplierService.update(supplier);
//        model.addAttribute("success", "Thêm nhà cung cấp thành công");
        return "redirect:/list-supplier";// Trả về failed nếu không thành công
    }

    @GetMapping("/delete-supplier/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteById(id);
        return "redirect:/list-supplier";
    }
    @GetMapping("/search-supplier")
    public String searchPro(String search, Model model){
        model.addAttribute("list", supplierService.search(search));
        return "supplier/sup_list";    }
}
