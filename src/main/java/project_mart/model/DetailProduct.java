package project_mart.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "chi_tiet_hang_hoa")
@Data
public class DetailProduct  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "id_hang")
    private Product products;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "id_lo_hang")
    private Consignment consignments;

    @Column(name = "han_su_dung")
    private Date hanSuDung;

    @Column(name = "so_luong")
    private Integer soLuong;




//    public Integer getSoLuong(){
//        return this.soLuong;
//    }
//    public Date getHanSuDung() {
//        return this.hanSuDung;
//    }
//    public Integer getGia(){
//        return this.gia;
//    }
}
