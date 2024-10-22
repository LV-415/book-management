package com.example.PhamThiThaoThiJ3Lay10.controllor;

import com.example.PhamThiThaoThiJ3Lay10.entity.Book;
import com.example.PhamThiThaoThiJ3Lay10.repository.BookRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletSach", value = {"/book"})
public class ServletSach extends HttpServlet {
    private BookRepository rps = new BookRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "detail":
                showDetail(req, resp);
                break;
            case "del":
                deleteBook(req, resp);
                break;
            default:
                showList(req, resp);
        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book s = rps.getOne(id);
        req.setAttribute("sua", s);
        redirectView(req, resp);
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectView(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);
        rps.xoa(id);
        redirectView(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                updateBook(req, resp);
                break;
            case "add":
                addBook(req, resp);
                break;
        }
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        int soTrang = Integer.parseInt(req.getParameter("soTrang"));
        double donGia = Double.parseDouble(req.getParameter("donGia"));
        Book s = new Book(ma, ten, soTrang, donGia);
        rps.add(s);
        redirectView(req, resp);
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        int soTrang = Integer.parseInt(req.getParameter("soTrang"));
        double donGia = Double.parseDouble(req.getParameter("donGia"));
        Book s = new Book(ma, ten, soTrang, donGia);
        rps.sua(id, s);
        redirectView(req, resp);
    }

    private void redirectView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Book> list = rps.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/View/table_sach.jsp").forward(req, resp);
    }
}
