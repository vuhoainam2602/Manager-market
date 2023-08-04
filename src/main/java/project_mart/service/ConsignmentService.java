package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_mart.model.Consignment;
import project_mart.repository.ConsignmentRepository;

@Service
public class ConsignmentService {
    @Autowired
    private ConsignmentRepository consignmentRepository;

    public Consignment add(Consignment consignment){
        consignment.setCheck(true);
        return consignmentRepository.save(consignment);
    }
}
