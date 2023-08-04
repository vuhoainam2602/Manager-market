package project_mart.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "hoa_don")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tong_tien")
    private Integer tongTien;

    @Column(name = "ngay_lap")
    private Date ngayLap;

    @Column(name = "`check`",columnDefinition = "default bit 1")
    private Boolean check;

    @Column(name = "loai")
    private String loai;
    // Many to One Có nhiều người ở 1 địa điểm.
    @ManyToOne
    @JoinColumn(name = "id_user") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user_hd;

    @OneToMany(mappedBy = "billOfSale", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<DetailBillSale> detailBillSales;

    @ManyToOne
    @JoinColumn(name = "id_the", nullable = true) // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PointCard card_bill;
}
