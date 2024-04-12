package fpt.poly.app.controllers;

import fpt.poly.app.entities.MauSac;
import fpt.poly.app.repositories.FastRepository;
import fpt.poly.app.utils.ValidateEx;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/mau-sac/create",  // GET
        "/mau-sac/store",   // POST
        "/mau-sac/edit",    // GET
        "/mau-sac/update",  // POST
        "/mau-sac/delete",  // GET
        "/mau-sac/index",   // GET
})
public class MauSacServlet extends HttpServlet {

    private ValidateEx validateEx = new ValidateEx();
    private FastRepository<MauSac> fastRepository = new FastRepository<>();

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
        request.setAttribute("dataList", fastRepository.getAll(MauSac.class));
        request.getRequestDispatcher("/views/mau-sac/index.jsp")
                .forward(request, response);
    }

    public void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        request.getRequestDispatcher("/views/mau-sac/create.jsp")
                .forward(request, response);
    }

    public void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "ma", "ten", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã", "Tên"}, "ma", "ten")) {
            response.sendRedirect("/app_war_exploded/mau-sac/create");
        } else {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            MauSac objInput = new MauSac(null, ma, ten, trangThai);
            validateEx.clear();
            fastRepository.create(objInput);
            response.sendRedirect("/app_war_exploded/mau-sac/index");
        }
    }

    public void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));
        MauSac item = fastRepository.findById(MauSac.class, id);
        if (validateEx.getSession() != null) {
            request.setAttribute("ma", validateEx.getSession().getAttribute("ma"));
            request.setAttribute("ten", validateEx.getSession().getAttribute("ten"));
        } else {
            request.setAttribute("ma", item.getMa());
            request.setAttribute("ten", item.getTen());
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("/views/mau-sac/edit.jsp")
                .forward(request, response);
    }

    public void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        validateEx.initSession(request);
        validateEx.setSession(request, "ma", "ten", "trangThai");
        if (validateEx.checkNull(request, new String[]{"Mã", "Tên"}, "ma", "ten")) {
            response.sendRedirect("/app_war_exploded/mau-sac/edit?id="+request.getParameter("id"));
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            int trangThai = request.getParameter("trangThai") == null ? 0 : 1;
            MauSac objInput = new MauSac(id, ma, ten, trangThai);
            validateEx.clear();
            fastRepository.update(objInput);
            response.sendRedirect("/app_war_exploded/mau-sac/index");
        }
    }

    public void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));
        fastRepository.remove(fastRepository.findById(MauSac.class, id));
        index(request, response);
    }
}
