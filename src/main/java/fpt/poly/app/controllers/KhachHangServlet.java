package fpt.poly.app.controllers;

import fpt.poly.app.entities.HoaDonChiTiet;
import fpt.poly.app.entities.KhachHang;
import fpt.poly.app.entities.KhachHang;
import fpt.poly.app.repositories.KhachHangRepository;
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
        "/khach-hang/index",
        "/khach-hang/create",
        "/khach-hang/store",
        "/khach-hang/edit",
        "/khach-hang/update",
        "/khach-hang/delete",
})
public class KhachHangServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();
    private KhachHangRepository khachHangRepository = new KhachHangRepository();

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
        request.setAttribute("dataList", khachHangRepository.getAll());
        request.getRequestDispatcher("/views/khach-hang/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/khach-hang/create.jsp")
                .forward(request, response);
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maKhachHang", "soDienThoai", "ten", "trangThai");
        validateEx.checkNull(request, new String[]{"Mã khách hàng", "Số điện thoại", "Tên"}, "maKhachHang", "soDienThoai", "ten");
        validateEx.checkNumberInter(request, null, new String[]{"Số điện thoại"}, "soDienThoai");
        if (validateEx.checkNull(request, new String[]{"Mã khách hàng", "Số điện thoại", "Tên"}, "maKhachHang", "soDienThoai", "ten")
                || validateEx.checkNumberInter(request, null, new String[]{"Số điện thoại"}, "soDienThoai")) {
            response.sendRedirect("/app_war_exploded/khach-hang/create");
        } else {
            String maKh = request.getParameter("maKhachHang");
            String soDienThoai = request.getParameter("soDienThoai");
            String ten = request.getParameter("ten");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            KhachHang objInput = new KhachHang(null, ten, soDienThoai, maKh, trangThai);
            validateEx.clear();
            khachHangRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/khach-hang/index");
        }
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        KhachHang item = khachHangRepository.findById(id);
        if (validateEx.getSession() != null) {
            request.setAttribute("maKhachHang", validateEx.getSession().getAttribute("maKhachHang"));
            request.setAttribute("ten", validateEx.getSession().getAttribute("ten"));
            request.setAttribute("soDienThoai", validateEx.getSession().getAttribute("soDienThoai"));
        } else {
            request.setAttribute("maKhachHang", item.getMaKhachHang());
            request.setAttribute("ten", item.getTen());
            request.setAttribute("soDienThoai", item.getSoDienThoai());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/khach-hang/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maKhachHang", "soDienThoai", "ten", "trangThai");
        validateEx.checkNull(request, new String[]{"Mã khách hàng", "Số điện thoại", "Tên"}, "maKhachHang", "soDienThoai", "ten");
        validateEx.checkNumberInter(request, null, new String[]{"Số điện thoại"}, "soDienThoai");
        if (validateEx.checkNull(request, new String[]{"Mã khách hàng", "Số điện thoại", "Tên"}, "maKhachHang", "soDienThoai", "ten")
                || validateEx.checkNumberInter(request, null, new String[]{"Số điện thoại"}, "soDienThoai")) {
            response.sendRedirect("/app_war_exploded/khach-hang/edit?id="+request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String maKh = request.getParameter("maKhachHang");
            String soDienThoai = request.getParameter("soDienThoai");
            String ten = request.getParameter("ten");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            KhachHang objInput = new KhachHang(id, ten, soDienThoai, maKh, trangThai);
            validateEx.clear();
            khachHangRepository.update(objInput);
            response.sendRedirect("/app_war_exploded/khach-hang/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        khachHangRepository.remove(khachHangRepository.findById(id));
        index(request, response);
    }
}