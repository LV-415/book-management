package com.example.PhamThiThaoThiJ3Lay10.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    private int id;
    private String ma;
    private String ten;
    private int soTrang;
    private double donGia;
    private int id_nxb;
    private int trangThai;

    public Book(String ma, String ten, int soTrang, double donGia) {
        this.ma = ma;
        this.ten = ten;
        this.soTrang = soTrang;
        this.donGia = donGia;
    }

    public Book(int id, String ma, String ten, int soTrang, double donGia) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.soTrang = soTrang;
        this.donGia = donGia;
    }
}
