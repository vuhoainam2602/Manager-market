package project_mart.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "hang_hoa")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_hang")
    private String tenHang;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia")
    private Integer gia;

    @Column(name = "han_su_dung")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hanSuDung;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_hang")
//    private Shelf shelf; // biến address này sẽ trùng  với giá trị  mappedBy trong Class User

    @OneToMany(mappedBy = "product_bill", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<DetailBillSale> detailBillSales;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<DetailProduct> detail = new ArrayList<>();

}
