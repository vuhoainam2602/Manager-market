package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.Bill;
import project_mart.model.PointCard;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query("select b from hoa_don b where b.check = true and b.loai != null order by b.id desc")
    List<Bill> findAll();


    Optional<Bill> findById(Long id);
}
