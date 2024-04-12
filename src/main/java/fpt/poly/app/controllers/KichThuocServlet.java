package fpt.poly.app.controllers;

import fpt.poly.app.entities.KichThuoc;
import fpt.poly.app.repositories.FastRepository;
import fpt.poly.app.utils.ValidateEx;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/kich-thuoc/index",
        "/kich-thuoc/create",
        "/kich-thuoc/store",
        "/kich-thuoc/edit",
        "/kich-thuoc/update",
        "/kich-thuoc/delete",
})
public class KichThuocServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();
    private FastRepository<KichThuoc> fastRepository = new FastRepository<>();

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
        request.setAttribute("dataList", fastRepository.getAll(KichThuoc.class));
        request.getRequestDispatcher("/views/kich-thuoc/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/kich-thuoc/create.jsp")
                .forward(request, response);
    }

    private boolean checkEquals(String maMau) {
        for (KichThuoc item : fastRepository.getAll(KichThuoc.class)) {
            if (item.getMa().equals(maMau)) {
                return true;
            }
        }
        return false;
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "ma", "ten", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã", "Tên"}, "ma", "ten")) {
            response.sendRedirect("/app_war_exploded/kich-thuoc/create");
            return;
        }
        if (checkEquals(request.getParameter("ma"))) {
            validateEx.setErr("ma", "Đã tồn tại mã " + request.getParameter("ma"));
            response.sendRedirect("/app_war_exploded/kich-thuoc/create");
            return;
        }

        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
        KichThuoc objInput = new KichThuoc(null, ma, ten, trangThai);
        validateEx.clear();
        fastRepository.create(objInput);
        response.sendRedirect("/app_war_exploded/kich-thuoc/index");
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        KichThuoc item = fastRepository.findById(KichThuoc.class, id);
        if (validateEx.getSession() != null) {
            request.setAttribute("ma", validateEx.getSession().getAttribute("ma"));
            request.setAttribute("ten", validateEx.getSession().getAttribute("ten"));
        } else {
            request.setAttribute("ma", item.getMa());
            request.setAttribute("ten", item.getTen());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/kich-thuoc/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "ma", "ten", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã", "Tên"}, "ma", "ten")) {
            response.sendRedirect("/app_war_exploded/kich-thuoc/edit?id="+request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            KichThuoc objInput = new KichThuoc(id, ma, ten, trangThai);
            validateEx.clear();
            fastRepository.update(objInput);
            response.sendRedirect("/app_war_exploded/kich-thuoc/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        fastRepository.remove(fastRepository.findById(KichThuoc.class, id));
        index(request, response);
    }
}