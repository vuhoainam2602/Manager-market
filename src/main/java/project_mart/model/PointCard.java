package project_mart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity(name = "the_tich_diem")
@Data
public class PointCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_khach_hang", nullable = false)
    private String tenKH;

    @Column(nullable = false)
    private String cccd;
    @Column(nullable = false)
    private String sdt;
    @Column(nullable = false)
    private Integer diem;

    @Column(name = "`check`",columnDefinition = "default bit 1")
    private Boolean check;

    // Many to One Có nhiều người ở 1 địa điểm.
    @ManyToOne
    @JoinColumn(name = "id_user") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user_point;

    @OneToMany(mappedBy = "card_bill", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Bill> billList;

}
