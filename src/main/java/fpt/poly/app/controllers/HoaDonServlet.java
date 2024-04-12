package fpt.poly.app.controllers;

import fpt.poly.app.entities.HoaDon;
import fpt.poly.app.repositories.HoaDonRepository;
import fpt.poly.app.repositories.KhachHangRepository;
import fpt.poly.app.repositories.NhanVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet({
        "/hoa-don/index",
        "/hoa-don/create",
        "/hoa-don/store",
        "/hoa-don/edit",
        "/hoa-don/update",
        "/hoa-don/delete",
})
public class HoaDonServlet extends HttpServlet {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
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
    ) throws IOException, ServletException  {
        request.setAttribute("dataList", hoaDonRepository.getAll());
        request.getRequestDispatcher("/views/hoa-don/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.setAttribute("lstNv", nhanVienRepository.getAll());
        request.setAttribute("lstKh", khachHangRepository.getAll());
        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String defaultDate = sdf.format(new Date());
        request.setAttribute("defaultDate", defaultDate);
        request.getRequestDispatcher("/views/hoa-don/create.jsp")
                .forward(request, response);
    }

    @SneakyThrows
    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int idKh = Integer.parseInt(request.getParameter("idKh").equals("") ? "0" : request.getParameter("idKh"));
        int idNv = Integer.parseInt(request.getParameter("idNv").equals("") ? "0" : request.getParameter("idNv"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayMuaHang = formatter.parse(request.getParameter("ngayMuaHang"));
        int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
        HoaDon objInput = new HoaDon(null, idKh, idNv, ngayMuaHang, trangThai);
        hoaDonRepository.create(objInput);
        response.sendRedirect("/app_war_exploded/hoa-don/index");
    }


    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        HoaDon obj = hoaDonRepository.findById(id);
        request.setAttribute("lstNv", nhanVienRepository.getAll());
        request.setAttribute("lstKh", khachHangRepository.getAll());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String defaultDate = sdf.format(obj.getNgayMuaHang());
        request.setAttribute("item", hoaDonRepository.findById(id));
        request.setAttribute("ngayMuaHang", defaultDate);
        request.getRequestDispatcher("/views/hoa-don/edit.jsp")
                .forward(request, response);
    }

    @SneakyThrows
    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idKh = Integer.parseInt(request.getParameter("idKh").equals("") ? "0" : request.getParameter("idKh"));
        int idNv = Integer.parseInt(request.getParameter("idNv").equals("") ? "0" : request.getParameter("idNv"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayMuaHang = formatter.parse(request.getParameter("ngayMuaHang"));
        int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
        HoaDon objInput = new HoaDon(id, idKh, idNv, ngayMuaHang, trangThai);
        hoaDonRepository.update(objInput);
        response.sendRedirect("/app_war_exploded/hoa-don/index");
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        hoaDonRepository.remove(hoaDonRepository.findById(id));
        response.sendRedirect("/app_war_exploded/hoa-don/index");
    }
}