package project_mart.repository;

import org.springframework.data.jpa.repository.Query;
import project_mart.model.PointCard;
import project_mart.model.Product;
import project_mart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project_mart.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select p from User p where p.check = true")
    List<User> findAll();

    @Query("SELECT p FROM User p where p.ten like %?1% or p.username like %?1% order by p.id desc")
    List<User> search(String key);
}
