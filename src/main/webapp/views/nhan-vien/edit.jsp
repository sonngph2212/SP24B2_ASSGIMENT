<%--
  Created by IntelliJ IDEA.
  User: sonst
  Date: 13/03/2024
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    String maNvErr = (String)session.getAttribute("maNvErr");
    String tenErr = (String)session.getAttribute("tenErr");
    String tenDangNhapErr = (String)session.getAttribute("tenDangNhapErr");
    String matKhauErr = (String)session.getAttribute("matKhauErr");
%>
<div class="container mt-5">
    <form method="POST" action="/app_war_exploded/nhan-vien/update?id=${item.id}">
        <div class="mb-3">
            <label class="form-label">Mã nhân viên</label>
            <input type="text" class="form-control" name="maNv" value="${maNv}">
            <% if (maNvErr != null) { %>
            <span style="color: red;"><%= maNvErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">Tên nhân viên</label>
            <input type="text" class="form-control" name="ten" value="${ten}">
            <% if (tenErr != null) { %>
            <span style="color: red;"><%= tenErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">Tên đăng nhập</label>
            <input type="text" class="form-control" name="tenDangNhap" value="${tenDangNhap}">
            <% if (tenDangNhapErr != null) { %>
            <span style="color: red;"><%= tenDangNhapErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">Mật khẩu</label>
            <input type="text" class="form-control" name="matKhau" value="${matKhau}">
            <% if (matKhauErr != null) { %>
            <span style="color: red;"><%= matKhauErr %></span>
            <% } %>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="trangThai" value="1" ${item.trangThai == 1 ? 'checked' : ''}>
            <label class="form-check-label">Hoạt động</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
