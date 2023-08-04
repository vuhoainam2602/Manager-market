package project_mart.model;

public class DetailBill_ProductDto {
    private String tenHang;
    private int soLuong;
    private int gia;
    private String loai;

    public DetailBill_ProductDto(String tenHang, int soLuong, int gia,String loai) {
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.gia = gia;
        this.loai = loai;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public String toString() {
        return "DetailBill_ProductDto{" +
                "tenHang='" + tenHang + '\'' +
                ", soLuong=" + soLuong +
                ", gia=" + gia +
                ", loai='" + loai + '\'' +
                '}';
    }
}
