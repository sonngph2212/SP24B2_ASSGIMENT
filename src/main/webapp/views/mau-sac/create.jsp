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
<body style="background-color: #8EC5FC;
background-image: linear-gradient(62deg, #8EC5FC 0%, #E0C3FC 100%);
">
<%
    String maErr = (String)session.getAttribute("maErr");
    String tenErr = (String)session.getAttribute("tenErr");
%>
<div class="card container p-5 mt-5">
    <div class="text-center">
        <h2>Thêm mới màu sắc</h2>
    </div>
    <form method="POST" action="/app_war_exploded/mau-sac/store">
        <div class="mb-3">
            <label class="form-label">Mã</label>
            <input type="text" class="form-control" name="ma" value="${ma}">
            <% if (maErr != null) { %>
            <span style="color: red;"><%= maErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">Tên màu</label>
            <input type="text" class="form-control" name="ten" value="${ten}">
            <% if (tenErr != null) { %>
            <span style="color: red;"><%= tenErr %></span>
            <% } %>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="trangThai" value="1">
            <label class="form-check-label">Hoạt động</label>
        </div>
        <div class="d-flex justify-content-end gap-2">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a class="btn btn-outline-primary nb-2" href="/app_war_exploded/">Quay lại</a>
        </div>
    </form>
</div>
<% session.invalidate(); %>
</body>
</html>
