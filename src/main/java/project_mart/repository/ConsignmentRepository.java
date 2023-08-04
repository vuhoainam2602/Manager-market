package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project_mart.model.Consignment;

public interface ConsignmentRepository extends JpaRepository<Consignment,Long> {

}
