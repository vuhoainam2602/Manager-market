package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import project_mart.model.Product;
import project_mart.model.Supplier;
import project_mart.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public Supplier add(Supplier supplier){
        boolean checkSup= Optional.ofNullable(supplier)
                .filter(t -> !StringUtils.isEmpty(t.getTenNcc())) // Kiểm tra title khác rỗng
                .filter(t -> !StringUtils.isEmpty(t.getSdt())) // Kiểm tra detail khác rỗng
                .isPresent(); // Trả về true nếu hợp lệ, ngược lại false

        if (checkSup){
            supplier.setCheck(true);
            return supplierRepository.save(supplier);
        }
        return null;
    }

    public Supplier update(Supplier supplier){
        supplier.setCheck(true);
        return supplierRepository.save(supplier);

    }

    public void deleteById(Long id){
        Supplier fromDB = supplierRepository.findById(id).orElse(null);
        if(fromDB == null){
            return;
        }

        fromDB.setCheck(false);
        supplierRepository.save(fromDB);
    }

    public Supplier findByID(Long id){
        return supplierRepository.findById(id).orElse(null);
    }
    public List<Supplier> search(String keyword){
        return  supplierRepository.search(keyword);
    }

}
