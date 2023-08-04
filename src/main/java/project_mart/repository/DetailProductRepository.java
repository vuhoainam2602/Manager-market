package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.DetailProduct;
import project_mart.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailProductRepository extends JpaRepository<DetailProduct, Long> {
    @Query("select p from chi_tiet_hang_hoa p")
    List<DetailProduct> findAll();

    @Query("SELECT p FROM chi_tiet_hang_hoa p where p.products.id = :id order by p.id desc")
    List<DetailProduct> findAllByProducts(Long id);
}
