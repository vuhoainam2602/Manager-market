package project_mart.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ProductDto {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
    }
}
