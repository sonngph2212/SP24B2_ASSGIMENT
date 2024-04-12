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
    <div class="mb-2">
        <h3 class="text-center">Bảng màu sắc</h3>
    </div>
    <div class="mb-2">
        <a class="btn btn-outline-primary nb-2" href="/app_war_exploded/">Quay lại</a>
        <a class="btn btn-outline-success nb-2" href="/app_war_exploded/mau-sac/create">Create</a>
    </div>
    <table class="table">
        <thead>
        <tr class="table-secondary">
            <th scope="col">ID</th>
            <th scope="col">Mã</th>
            <th scope="col">Tên</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ dataList }" var="el" varStatus="status">
            <tr>
                <td>${ status.index + 1 }</td>
                <td>${ el.ma }</td>
                <td>${ el.ten }</td>
                <td>${ el.trangThai }</td>
                <td>
                    <a class="btn btn-outline-warning" href="/app_war_exploded/mau-sac/edit?id=${el.id}">Update</a>
                    <a onclick="return confirm('Bạn có chắc chắn muốn xóa?')" class="btn btn-outline-danger" href="/app_war_exploded/mau-sac/delete?id=${el.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
