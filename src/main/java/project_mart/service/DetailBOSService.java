package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_mart.model.DetailBillSale;
import project_mart.model.DetailBill_ProductDto;
import project_mart.repository.DetailBOSRepository;

import java.util.List;

@Service
public class DetailBOSService {
    @Autowired
    private DetailBOSRepository detailBOSRepository;

    public DetailBillSale add(DetailBillSale detailBillSale){
        return detailBOSRepository.save(detailBillSale);
    }

    public List<DetailBill_ProductDto> findTenHangSaleById(Long id){
        return detailBOSRepository.findTenHangSaleById(id);
    }

    public List<DetailBill_ProductDto> findTenHangImportById(Long id){
        return detailBOSRepository.findTenHangImportById(id);
    }
}
