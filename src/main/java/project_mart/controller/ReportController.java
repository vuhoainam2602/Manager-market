package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project_mart.model.Bill;
import project_mart.model.Product;
import project_mart.service.BillService;
import project_mart.service.ProductService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ReportController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BillService billService;

    @GetMapping("/han-su-dung")
    public String han_su_dung(Model model){
        List<Product> productList = productService.findAll();
        List<Product> hethan = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        productList.forEach(product -> {
            if (date.after(product.getHanSuDung())){
                hethan.add(product);
            }
        });
        System.out.println(hethan);
        model.addAttribute("check","1");
        model.addAttribute("list", hethan);
        return "product/pro_list";
    }

    @GetMapping("/tien-chi")
    public String tien_chi(Model model){
        List<Bill> billList = billService.findAll();
        Date date = new Date(System.currentTimeMillis());
        AtomicInteger tong_tien = new AtomicInteger();
        billList.forEach(bill -> {
            if (bill.getNgayLap().getYear() == date.getYear() && bill.getNgayLap().getMonth() == date.getMonth()){
                System.out.println(bill.getTongTien());
                if (Objects.equals(bill.getLoai(), "nhap")){
                    tong_tien.addAndGet(bill.getTongTien());
                }
            }
        });
        DecimalFormat df = new DecimalFormat("###,###,###,###,###");

        model.addAttribute("check",1);
        model.addAttribute("tong_tien", df.format(tong_tien));
        return "report/thu_chi";
    }

    @GetMapping("/tien-thu")
    public String tien_thu(Model model){
        List<Bill> billList = billService.findAll();
        Date date = new Date(System.currentTimeMillis());
        AtomicInteger tong_tien = new AtomicInteger();
        billList.forEach(bill -> {
            if (bill.getNgayLap().getYear() == date.getYear() && bill.getNgayLap().getMonth() == date.getMonth()){
                System.out.println(bill.getTongTien());
                if (Objects.equals(bill.getLoai(), "ban")){
                    tong_tien.addAndGet(bill.getTongTien());
                }
            }
        });
        DecimalFormat df = new DecimalFormat("###,###,###,###,###");

        model.addAttribute("check",0);
        model.addAttribute("tong_tien", df.format(tong_tien));
        return "report/thu_chi";
    }
}
