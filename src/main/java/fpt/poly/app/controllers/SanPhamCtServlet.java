package fpt.poly.app.controllers;

import fpt.poly.app.entities.*;
import fpt.poly.app.repositories.FastRepository;
import fpt.poly.app.utils.ValidateEx;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet({
        "/san-pham-ct/index",
        "/san-pham-ct/create",
        "/san-pham-ct/store",
        "/san-pham-ct/edit",
        "/san-pham-ct/update",
        "/san-pham-ct/delete",
})
public class SanPhamCtServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();
    private FastRepository<SanPhamCt> fastRepository = new FastRepository<>();
    private FastRepository<SanPham> sanPhamFastRepository = new FastRepository<>();
    private FastRepository<MauSac> mauSacFastRepository = new FastRepository<>();
    private FastRepository<KichThuoc> kichThuocFastRepository = new FastRepository<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.setAttribute("dataList", fastRepository.getAll(SanPhamCt.class));
        request.getRequestDispatcher("/views/san-pham-ct/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.setAttribute("lstSp", sanPhamFastRepository.getAll(SanPham.class));
        request.setAttribute("lstMs", mauSacFastRepository.getAll(MauSac.class));
        request.setAttribute("lstKt", kichThuocFastRepository.getAll(KichThuoc.class));
        request.getRequestDispatcher("/views/san-pham-ct/create.jsp")
                .forward(request, response);
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maSanPhamCt", "idSanPham", "idKichThuoc", "idMauSac", "soLuong", "donGia", "trangThai");
        validateEx.checkNull(request, new String[]{"Mã sản phẩm chi tiết"}, "maSanPhamCt");
        validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong");
        validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia");
        if (validateEx.checkNull(request, new String[]{"Mã sản phẩm chi tiết"}, "maSanPhamCt")
                || validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong")
                || validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia")) {
            response.sendRedirect("/app_war_exploded/san-pham-ct/create");
        } else {
            String maSanPhamCt = request.getParameter("maSanPhamCt");
            int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
            int idMauSac = Integer.parseInt(request.getParameter("idMauSac"));
            int idKichThuoc = Integer.parseInt(request.getParameter("idKichThuoc"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            float donGia = Float.parseFloat(request.getParameter("donGia"));
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            SanPhamCt objInput = new SanPhamCt(null, maSanPhamCt, idKichThuoc, idMauSac, idSanPham, soLuong, donGia, trangThai);
            validateEx.clear();
            fastRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/san-pham-ct/index");
        }
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("lstSp", sanPhamFastRepository.getAll(SanPham.class));
        request.setAttribute("lstMs", mauSacFastRepository.getAll(MauSac.class));
        request.setAttribute("lstKt", kichThuocFastRepository.getAll(KichThuoc.class));
        SanPhamCt item = fastRepository.findById(SanPhamCt.class, id);
        if (validateEx.getSession() != null) {
            request.setAttribute("maSanPhamCt", validateEx.getSession().getAttribute("maSanPhamCt"));
            request.setAttribute("soLuong", validateEx.getSession().getAttribute("soLuong"));
            request.setAttribute("donGia", validateEx.getSession().getAttribute("donGia"));
        } else {
            request.setAttribute("maSanPhamCt", item.getMaSanPhamCt());
            request.setAttribute("soLuong", item.getSoLuong());
            request.setAttribute("donGia", item.getDonGia());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/san-pham-ct/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maSanPhamCt", "idSanPham", "idKichThuoc", "idMauSac", "soLuong", "donGia", "trangThai");
        validateEx.checkNull(request, new String[]{"Mã sản phẩm chi tiết"}, "maSanPhamCt");
        validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong");
        validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia");
        if (validateEx.checkNull(request, new String[]{"Mã sản phẩm chi tiết"}, "maSanPhamCt")
                || validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong")
                || validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia")) {
            response.sendRedirect("/app_war_exploded/san-pham-ct/edit?id=" + request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String maSanPhamCt = request.getParameter("maSanPhamCt");
            int idSanPham = Integer.parseInt(request.getParameter("idSanPham"));
            int idMauSac = Integer.parseInt(request.getParameter("idMauSac"));
            int idKichThuoc = Integer.parseInt(request.getParameter("idKichThuoc"));
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            float donGia = Float.parseFloat(request.getParameter("donGia"));
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            SanPhamCt objInput = new SanPhamCt(id, maSanPhamCt, idKichThuoc, idMauSac, idSanPham, soLuong, donGia, trangThai);
            validateEx.clear();
            fastRepository.update(objInput);
            response.sendRedirect("/app_war_exploded/san-pham-ct/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        fastRepository.remove(fastRepository.findById(SanPhamCt.class, id));
        index(request, response);
    }
}