package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_mart.model.*;
import project_mart.repository.BillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailBOSService detailBOSService;

    @Autowired
    private PointCardService pointCardService;

    public List<Bill> findAll() {
        return billRepository.findAll();
    }


    public Bill add(Bill bill) {
        bill.setCheck(true);
        return billRepository.save(bill);
    }

    // tien nhap
    public Integer money_import(ProductDto products){
        int tong_tien = 0;

        for (int i = 0; i < products.getProducts().size(); i++) {
            tong_tien += products.getProducts().get(i).getSoLuong()* products.getProducts().get(i).getGia()*0.6;
        }

        return tong_tien;
    }

    //tien bán
    public Integer money_sale(ProductDto products, Bill bill){
        List<Product> product_money = new ArrayList<>();
        products.getProducts().forEach(p -> {
            Product product = productService.findById(p.getId());
            product_money.add(product);
        });
        int tong_tien = 0;

        for (int i = 0; i < products.getProducts().size(); i++) {
            tong_tien += products.getProducts().get(i).getSoLuong()* product_money.get(i).getGia();
        }


        if (bill.getCard_bill() != null){
            PointCard pointCard = pointCardService.findByID(bill.getCard_bill().getId());
            if (pointCard.getDiem() >=0 && pointCard.getDiem() <=20){
                tong_tien *= 0.9;
            }else{
                tong_tien *= 0.8;
            }
            //tăng 1 điểm
            pointCardService.increasePoint(bill.getCard_bill().getId());
        }else {
            System.out.println("không có thẻ");
        }



        return tong_tien;
    }

    public void setBillSale(Bill bill,ProductDto products){
        for (int i = 0; i < products.getProducts().size(); i++) {
            DetailBillSale detailBillSale = new DetailBillSale();
            Product product = productService.findById(products.getProducts().get(i).getId());
            System.out.println(product);
            detailBillSale.setBillOfSale(bill);
            detailBillSale.setProduct_bill(products.getProducts().get(i));
            if (product.getSoLuong() >= products.getProducts().get(i).getSoLuong()){
                detailBillSale.setSoLuong(products.getProducts().get(i).getSoLuong());
            }else {
                detailBillSale.setSoLuong(product.getSoLuong());
            }
            System.out.println(detailBillSale);
            detailBOSService.add(detailBillSale);
        }
    }

    public void updateQuantityProduct(ProductDto products){
        products.getProducts().forEach(p -> {
            Product product = productService.findById(p.getId());
            int soLuong = product.getSoLuong() - p.getSoLuong();
            if (soLuong >= 0){
                product.setSoLuong(soLuong);
            }else {
                product.setSoLuong(0);
            }

            System.out.println(product);
            productService.update(product);
        });
    }

    public Bill findById(Long id){
        return billRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        Bill fromDB = billRepository.findById(id).orElse(null);
        if(fromDB == null){
            return;
        }

        fromDB.setCheck(false);
        billRepository.save(fromDB);
    }
}
