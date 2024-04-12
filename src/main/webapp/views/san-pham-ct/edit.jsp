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
    String maSanPhamCtErr = (String) session.getAttribute("maSanPhamCtErr");
    String soLuongErr = (String) session.getAttribute("soLuongErr");
    String donGiaErr = (String) session.getAttribute("donGiaErr");
%>
<div class="container mt-5">
    <form method="POST" action="/app_war_exploded/san-pham-ct/update?id=${item.id}">
        <div class="mb-3">
            <label class="form-label">Mã sản phẩm chi tiết</label>
            <input type="text" class="form-control" name="maSanPhamCt" value="${maSanPhamCt}">
            <% if (maSanPhamCtErr != null) { %>
            <span style="color: red;"><%= maSanPhamCtErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">Sản phẩm</label>
            <select class="form-select" name="idSanPham">
                <c:forEach items="${lstSp}" var="el">
                    <option value="${el.id}" ${el.id == idSanPham ? 'selected' : ''}>${el.ma} - ${el.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Kích thước</label>
            <select class="form-select" name="idKichThuoc">
                <c:forEach items="${lstKt}" var="el">
                    <option value="${el.id}" ${el.id == idKichThuoc ? 'selected' : ''}>${el.ma} - ${el.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Màu sắc</label>
            <select class="form-select" name="idMauSac">
                <c:forEach items="${lstKt}" var="el">
                    <option value="${el.id}" ${el.id == idMauSac ? 'selected' : ''}>${el.ma} - ${el.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">số lượng</label>
            <input type="number" class="form-control" name="soLuong" value="${soLuong}">
            <% if (soLuongErr != null) { %>
            <span style="color: red;"><%= soLuongErr %></span>
            <% } %>
        </div>
        <div class="mb-3">
            <label class="form-label">đơn giá</label>
            <input type="number" class="form-control" name="donGia" value="${donGia}">
            <% if (donGiaErr != null) { %>
            <span style="color: red;"><%= donGiaErr %></span>
            <% } %>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="trangThai"
                   value="1" ${item.trangThai == 1 ? 'checked' : ''}>
            <label class="form-check-label">Hoạt động</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
