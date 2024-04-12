package fpt.poly.app.controllers;

import fpt.poly.app.entities.NhanVien;
import fpt.poly.app.repositories.FastRepository;
import fpt.poly.app.utils.ValidateEx;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/nhan-vien/index",
        "/nhan-vien/create",
        "/nhan-vien/store",
        "/nhan-vien/edit",
        "/nhan-vien/update",
        "/nhan-vien/delete",
})
public class NhanVienServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();
    private FastRepository<NhanVien> fastRepository = new FastRepository<>();

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
        request.setAttribute("dataList", fastRepository.getAll(NhanVien.class));
        request.getRequestDispatcher("/views/nhan-vien/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/nhan-vien/create.jsp")
                .forward(request, response);
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maNv", "ten", "tenDangNhap", "matKhau", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã nhân viên", "Tên", "Tên đăng nhập", "Mật khẩu"}, "maNv", "ten", "tenDangNhap", "matKhau")) {
            response.sendRedirect("/app_war_exploded/nhan-vien/create");
        } else {
            String maNv = request.getParameter("maNv");
            String ten = request.getParameter("ten");
            String tenDangNhap = request.getParameter("tenDangNhap");
            String matKhau = request.getParameter("matKhau");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            NhanVien objInput = new NhanVien(null, ten, maNv, tenDangNhap, matKhau, trangThai);
            validateEx.clear();
            fastRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/nhan-vien/index");
        }
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));
        NhanVien item = fastRepository.findById(NhanVien.class, id);
        if (validateEx.getSession() != null) {
            request.setAttribute("maNv", validateEx.getSession().getAttribute("maNv"));
            request.setAttribute("ten", validateEx.getSession().getAttribute("ten"));
            request.setAttribute("tenDangNhap", validateEx.getSession().getAttribute("tenDangNhap"));
            request.setAttribute("matKhau", validateEx.getSession().getAttribute("matKhau"));
        } else {
            request.setAttribute("maNv", item.getMaNv());
            request.setAttribute("ten", item.getTen());
            request.setAttribute("tenDangNhap", item.getTenDangNhap());
            request.setAttribute("matKhau", item.getMatKhau());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/nhan-vien/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "maNv", "ten", "tenDangNhap", "matKhau", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã nhân viên", "Tên", "Tên đăng nhập", "Mật khẩu"}, "maNv", "ten", "tenDangNhap", "matKhau")) {
            response.sendRedirect("/app_war_exploded/nhan-vien/edit?id="+request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String maNv = request.getParameter("maNv");
            String ten = request.getParameter("ten");
            String tenDangNhap = request.getParameter("tenDangNhap");
            String matKhau = request.getParameter("matKhau");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            NhanVien objInput = new NhanVien(id, ten, maNv, tenDangNhap, matKhau, trangThai);
            validateEx.clear();
            fastRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/nhan-vien/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        fastRepository.remove(fastRepository.findById(NhanVien.class, id));
        index(request, response);
    }
}