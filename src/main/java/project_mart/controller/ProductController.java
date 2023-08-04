package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project_mart.model.DetailProduct;
import project_mart.service.DetailProductService;
import project_mart.service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private DetailProductService detailProductService;


    @GetMapping("/list-product")
    public String index( Model model) {
        model.addAttribute("list", productService.findAll());
        return "product/pro_list";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/list-product";
    }

    @GetMapping("/detail-product/{id}")
    public String detailProduct(Model model, @PathVariable Long id) {
        List<DetailProduct> detailProducts = detailProductService.findAllByProducts(id);
        System.out.println(productService.findById(id));
        detailProducts.forEach(detailProduct -> System.out.println(detailProduct));
        model.addAttribute("ten", productService.findById(id));
        model.addAttribute("list", detailProducts);
        return "product/detail";
    }

    @GetMapping("/search-product")
    public String searchPro(String search, Model model){
        model.addAttribute("list", productService.search(search));
        return "product/pro_list";
    }

}
