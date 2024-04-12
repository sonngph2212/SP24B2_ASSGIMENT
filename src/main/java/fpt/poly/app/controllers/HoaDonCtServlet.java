package fpt.poly.app.controllers;

import fpt.poly.app.entities.HoaDonChiTiet;
import fpt.poly.app.repositories.HoaDonCtRepository;
import fpt.poly.app.repositories.HoaDonRepository;
import fpt.poly.app.repositories.SanPhamCtRepository;
import fpt.poly.app.utils.ValidateEx;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/hoa-don-ct/index",
        "/hoa-don-ct/create",
        "/hoa-don-ct/store",
        "/hoa-don-ct/edit",
        "/hoa-don-ct/update",
        "/hoa-don-ct/delete",
})
public class HoaDonCtServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();

    private HoaDonCtRepository hoaDonCtRepository = new HoaDonCtRepository();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private SanPhamCtRepository sanPhamCtRepository = new SanPhamCtRepository();

    public HoaDonCtServlet() {

    }

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
        request.setAttribute("dataList", hoaDonCtRepository.getAll());
        request.getRequestDispatcher("/views/hoa-don-ct/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.setAttribute("lstHd", hoaDonRepository.getAll());
        request.setAttribute("lstSpct", sanPhamCtRepository.getAll());
        request.getRequestDispatcher("/views/hoa-don-ct/create.jsp")
                .forward(request, response);
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "idHd", "idSanPhamCt", "soLuong", "donGia");
        validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong");
        validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia");
        if (validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong")
                || validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia")) {
            response.sendRedirect("/app_war_exploded/hoa-don-ct/create");
        } else {
            int idHoaDon = Integer.parseInt(request.getParameter("idHoaDon"));
            int idSanPhamCt = Integer.parseInt(request.getParameter("idSanPhamCt"));
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            float donGia = Float.parseFloat(request.getParameter("donGia"));
            validateEx.clear();
            HoaDonChiTiet objInput = new HoaDonChiTiet(null, idHoaDon, idSanPhamCt, soLuong, donGia, trangThai);
            hoaDonCtRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/hoa-don-ct/index");
        }
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("lstHd", hoaDonRepository.getAll());
        request.setAttribute("lstSpct", sanPhamCtRepository.getAll());
        HoaDonChiTiet item = hoaDonCtRepository.findById(id);
        if (validateEx.getSession() != null) {
            request.setAttribute("soLuong", validateEx.getSession().getAttribute("soLuong"));
            request.setAttribute("donGia", validateEx.getSession().getAttribute("donGia"));
        } else {
            request.setAttribute("soLuong", item.getSoLuong());
            request.setAttribute("donGia", item.getDonGia());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/hoa-don-ct/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "idHd", "idSanPhamCt", "soLuong", "donGia");
        validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong");
        validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia");
        if (validateEx.checkNumberInter(request, 0, new String[]{"Số lượng"}, "soLuong")
                || validateEx.checkNumberFloat(request, 0, new String[]{"Đơn giá"}, "donGia")) {
            response.sendRedirect("/app_war_exploded/hoa-don-ct/edit?id=" + request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            int idHoaDon = Integer.parseInt(request.getParameter("idHoaDon"));
            int idSanPhamCt = Integer.parseInt(request.getParameter("idSanPhamCt"));
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            float donGia = Float.parseFloat(request.getParameter("donGia"));
            validateEx.clear();
            HoaDonChiTiet objInput = new HoaDonChiTiet(id, idHoaDon, idSanPhamCt, soLuong, donGia, trangThai);
            hoaDonCtRepository.update(objInput);
            response.sendRedirect("/app_war_exploded/hoa-don-ct/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        hoaDonCtRepository.delete(hoaDonCtRepository.findById(id));
        index(request, response);
    }
}