package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_mart.model.*;
import project_mart.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DetailProductService detailProductService;

    public List<Product> findAll(){
        List<Product> productList= productRepository.findAll();
        List<Product> conHang = new ArrayList<>();
        productList.forEach(product -> {
            if(product.getSoLuong() !=0){
                conHang.add(product);
            }
        });
        return conHang;
    }

    public Optional<Product> add(String ten, Product product, Consignment consignment){
        Product db = productRepository.findByTenHang(ten).orElse(null);

        if (db == null){
            productRepository.save(product);
            DetailProduct detailProduct = new DetailProduct();
            detailProduct.setConsignments(consignment);
            detailProduct.setProducts(product);
            detailProduct.setHanSuDung(product.getHanSuDung());
            detailProduct.setSoLuong(product.getSoLuong());
            detailProductService.add(detailProduct);
        }else {
            int tong_sl = db.getSoLuong() + product.getSoLuong();
            db.setSoLuong(tong_sl);
            db.setHanSuDung(product.getHanSuDung());
            db.setGia(product.getGia());
            productRepository.saveAndFlush(db);
            DetailProduct detailProduct = new DetailProduct();
            detailProduct.setConsignments(consignment);
            detailProduct.setProducts(db);
            detailProduct.setHanSuDung(product.getHanSuDung());
            detailProduct.setSoLuong(product.getSoLuong());
            detailProductService.add(detailProduct);
        }
        return null;
    }

    public Product update(Product product){
        return productRepository.save(product);
    }
    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id){
        Product fromDB = productRepository.findById(id).orElse(null);
        if(fromDB == null){
            return;
        }

        fromDB.setSoLuong(0);
        productRepository.save(fromDB);
    }

    public List<Product> search(String keyword){
        return  productRepository.search(keyword);
    }
}
