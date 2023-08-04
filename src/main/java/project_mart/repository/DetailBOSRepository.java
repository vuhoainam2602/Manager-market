package project_mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project_mart.model.DetailBillSale;
import project_mart.model.DetailBill_ProductDto;


import java.util.List;

@Repository
public interface DetailBOSRepository extends JpaRepository<DetailBillSale,Long> {

//    @Query("SELECT h.tenHang, b.soLuong from hang_ban b inner join b.product_bill h where  b.billOfSale.id = :id")
    @Query("SELECT new project_mart.model.DetailBill_ProductDto(h.tenHang, b.soLuong,h.gia, b.billOfSale.loai) from hang_hoa h, hang_ban b where b.product_bill = h and b.billOfSale.id = :id and b.billOfSale.loai = 'ban' ")
    List<DetailBill_ProductDto> findTenHangSaleById(Long id);


    @Query("SELECT new project_mart.model.DetailBill_ProductDto(h.tenHang, c.soLuong, h.gia, l.bill.loai) from  hang_hoa h, chi_tiet_hang_hoa c, lo_hang l where c.consignments =l and c.products = h and l.bill.id = :id and l.bill.loai = 'nhap'")
    List<DetailBill_ProductDto> findTenHangImportById(Long id);
//    @Query("SELECT p FROM hang_ban p where p.billOfSale.id = :id order by p.id asc")
//    List<DetailBillSale> findTenHangById(Long id);
}
