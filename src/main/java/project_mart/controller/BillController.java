package project_mart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project_mart.model.*;
import project_mart.service.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ConsignmentService consignmentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PointCardService pointCardService;

    @Autowired
    private DetailBOSService detailBOSService;


    //Danh sách bill
    @GetMapping("/list-bill")
    public String index_import(Model model){
        model.addAttribute("list_bill", billService.findAll());
        return "product/list_bill";
    }

    // quá trình nhập hàng
    @GetMapping("/import-count")
    public String import_count(Model model){
        model.addAttribute("import_count", new Count());
        return "product/import_count";
    }

    @PostMapping("/import-count")
    public String import_count(@ModelAttribute("import_count") Count count){
        System.out.println(count);
        return "redirect:/add-bill-import/" + count.getCount();
    }

    @GetMapping("/add-bill-import/{count}")
    public String addImport(Model model, @PathVariable int count){
        model.addAttribute("count", count);
        model.addAttribute("bill", new Bill());
        model.addAttribute("lo", new Consignment());
        model.addAttribute("product", new Product());
        model.addAttribute("list_user", userService.findAll());
        model.addAttribute("list_supplier", supplierService.findAll());
//        List<Product> products = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        for (int i = 0; i < count; i++) {
            productDto.addProduct(new Product());
        }

        model.addAttribute("productList", productDto);
        return "product/import_product";
    }

    @PostMapping("/add-bill-import")
    public String addBillImport(@ModelAttribute("bill") Bill bill,@ModelAttribute("lo") Consignment consignment,
                                @ModelAttribute("productList") ProductDto products){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        bill.setNgayLap(date);
        bill.setLoai("nhap");

        bill.setTongTien(billService.money_import(products));
        billService.add(bill);
        consignment.setBill(bill);
        consignmentService.add(consignment);
        products.getProducts().forEach(product -> {
            productService.add(product.getTenHang(), product, consignment);
        });
        return "redirect:/list-bill";
    }
    // ========================================================================================================


    //quá trình bán hàng
    @GetMapping("/sale-count")
    public String sale_count(Model model){
        model.addAttribute("sale_count", new Count());
        return "product/sale_count";
    }

    @PostMapping("/sale-count")
    public String sale_count(@ModelAttribute("sale_count") Count count){
        System.out.println(count);
        return "redirect:/add-bill-sale/" + count.getCount();
    }

    @GetMapping("/add-bill-sale/{count}")
    public String addSale(Model model, @PathVariable int count){
        model.addAttribute("count", count);
        model.addAttribute("bill", new Bill());
        model.addAttribute("product", productService.findAll());
        model.addAttribute("list_user", userService.findAll());
        model.addAttribute("list_card", pointCardService.findAll());
//        List<Product> products = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        for (int i = 0; i < count; i++) {
            productDto.addProduct(new Product());
        }

        model.addAttribute("productList", productDto);
        return "product/sale_product";
    }

    @PostMapping("/add-bill-sale")
    public String addBillImport(@ModelAttribute("bill") Bill bill,
                                @ModelAttribute("productList") ProductDto products){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        System.out.println(bill);
        System.out.println(bill.getUser_hd());
        System.out.println(bill.getCard_bill());
        bill.setNgayLap(date);
        bill.setLoai("ban");
        bill.setTongTien(billService.money_sale(products,bill));
        billService.add(bill);

        billService.setBillSale(bill,products);

        billService.updateQuantityProduct(products);
        return "redirect:/list-bill";
    }

    //==============================================================================================================

    //Chi tiết hàng hóa
    @GetMapping("/detail-bill-sale/{id}")
    public String detailBillSale(Model model, @PathVariable Long id) {
        List<DetailBill_ProductDto> detailBillProductDtoList = detailBOSService.findTenHangSaleById(id);
        PointCard pointCard = billService.findById(id).getCard_bill();
        detailBillProductDtoList.forEach(d -> System.out.println(d));
        model.addAttribute("list", detailBillProductDtoList);

        DecimalFormat df = new DecimalFormat("###,###,###,###,###");

        model.addAttribute("tong_tien",df.format(billService.findById(id).getTongTien()));
        model.addAttribute("giam", pointCard);

        return "product/detail_bill";
    }

    @GetMapping("/detail-bill-import/{id}")
    public String detailBillImport(Model model, @PathVariable Long id) {
        List<DetailBill_ProductDto> detailBillProductDtoList = detailBOSService.findTenHangImportById(id);
        detailBillProductDtoList.forEach(d -> System.out.println(d));
        model.addAttribute("list", detailBillProductDtoList);

        DecimalFormat df = new DecimalFormat("###,###,###,###,###");

        model.addAttribute("tong_tien",df.format(billService.findById(id).getTongTien()));

        return "product/detail_bill";
    }

    @GetMapping("/delete-bill/{id}")
    public String deleteBill(@PathVariable("id") Long id) {
        billService.deleteById(id);
        return "redirect:/list-bill";
    }
}
