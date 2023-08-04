package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM hang_hoa p order by p.id desc")
    List<Product> findAll();

    @Query("SELECT p FROM hang_hoa p where p.tenHang = :tenHang order by p.id desc")
    Optional<Product> findByTenHang(String tenHang);

    @Query("SELECT p FROM hang_hoa p where p.id = :id order by p.id desc")
    Optional<Product> findById(Long id);


    @Query("SELECT p FROM hang_hoa p where p.tenHang like %?1% order by p.id desc")
    List<Product> search(String key);
}