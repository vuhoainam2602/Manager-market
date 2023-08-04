package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.Product;
import project_mart.model.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Query("SELECT p FROM nha_cung_cap p where p.check = true order by p.id desc")
    List<Supplier> findAll();

    @Query("SELECT p FROM nha_cung_cap p where p.tenNcc like %?1% order by p.id desc")
    List<Supplier> search(String key);
}
