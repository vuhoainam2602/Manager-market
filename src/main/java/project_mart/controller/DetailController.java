package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project_mart.model.DetailProduct;
import project_mart.model.PointCard;
import project_mart.repository.DetailProductRepository;
import project_mart.service.DetailProductService;

import java.util.List;

@Controller
public class DetailController {
    @Autowired
    private DetailProductService detailProductService;

    @GetMapping("/detail")
    public ResponseEntity<List<DetailProduct>> findAll(){
        return ResponseEntity.ok(detailProductService.findAll());
    }
}
