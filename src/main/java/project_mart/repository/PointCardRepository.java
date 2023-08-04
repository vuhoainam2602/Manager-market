package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.PointCard;
import project_mart.model.Product;

import java.util.List;

@Repository
public interface PointCardRepository extends JpaRepository<PointCard,Long> {
    @Query("select p from the_tich_diem p where p.check = true")
    List<PointCard> findAll();

    @Query("SELECT p FROM the_tich_diem p where p.tenKH like %?1% order by p.id desc")
    List<PointCard> search(String key);
}
