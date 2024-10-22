package com.example.PhamThiThaoThiJ3Lay10.repository;

import com.example.PhamThiThaoThiJ3Lay10.entity.Book;
import com.example.PhamThiThaoThiJ3Lay10.until.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public BookRepository() {
        con = DBConnect.getConnection();
    }

    public ArrayList<Book> getAll() {
        sql = "select id, ma, ten, so_trang, don_gia, id_nxb, trang_thai from sach";
        ArrayList<Book> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book s = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int xoa(int id) {
        sql = "delete from sach\n" +
                "where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(int id, Book s) {
        sql = "update sach\n" +
                "set ma=?, ten=?, so_trang=?, don_gia=?\n" +
                "where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, s.getMa());
            ps.setObject(2, s.getTen());
            ps.setObject(3, s.getSoTrang());
            ps.setObject(4, s.getDonGia());
            ps.setObject(5, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Book getOne(int id) {
        sql = "select id, ma, ten, so_trang, don_gia, id_nxb, trang_thai from sach\n" +
                "where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Book s = new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Book s) {
        String sql = "INSERT INTO sach (ma, ten, so_trang, don_gia) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, s.getMa());
            ps.setObject(2, s.getTen());
            ps.setObject(3, s.getSoTrang());
            ps.setObject(4, s.getDonGia());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
