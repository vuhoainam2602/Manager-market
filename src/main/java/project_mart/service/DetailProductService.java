package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_mart.model.DetailProduct;
import project_mart.model.Product;
import project_mart.repository.DetailProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailProductService {
    @Autowired
    private DetailProductRepository detailProductRepository;

    public List<DetailProduct> findAll(){
        return detailProductRepository.findAll();
    }

    public DetailProduct add(DetailProduct detailProduct){
        return detailProductRepository.save(detailProduct);
    }

    public List<DetailProduct> findAllByProducts(Long id){
        return detailProductRepository.findAllByProducts(id);
    }
}
