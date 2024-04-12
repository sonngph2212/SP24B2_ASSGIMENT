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
<div class="container mt-5">
    <div class="text-center mb-5">
        <h4>Sửa hóa hóa đơn</h4>
    </div>
    <form method="POST" action="/app_war_exploded/hoa-don/update?id=${item.id}">
        <div class="mb-3">
            <label class="form-label">Khách hàng</label>
            <select class="form-select" name="idKh">
                <c:forEach items="${lstKh}" var="el">
                    <option value="${el.id}" ${item.idKh == el.id ? 'selected' : ''}>${el.maKhachHang} - ${el.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Nhân viên</label>
            <select class="form-select" name="idNv">
                <c:forEach items="${lstNv}" var="el">
                    <option value="${el.id}" ${item.idNv == el.id ? 'selected' : ''}>${el.tenDangNhap} - ${el.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Ngày mua hàng</label>
            <input type="date" class="form-control" name="ngayMuaHang" value="${ngayMuaHang}">
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="trangThai" value="1" ${item.trangThai == 1 ? 'checked' : ''}>
            <label class="form-check-label">Hoạt động</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script>
    document.getElementById("myForm").addEventListener("submit", function(event){
        if (!confirm("Bạn có muốn sửa bản ghi không?")) {
            event.preventDefault();
            alert("Từ chối")
        } else {
            alert("đồng ý")
        }
    });
</script>
</body>
</html>
