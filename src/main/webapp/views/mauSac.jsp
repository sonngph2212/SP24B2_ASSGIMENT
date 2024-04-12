<%--
  Created by IntelliJ IDEA.
  User: sonst
  Date: 08/03/2024
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/app_war_exploded/mau-sac/create" method="post">
    <label>Mã màu</label><br>
    <input type="text" name="ma"><br>
    <label>Mã màu</label><br>
    <input type="text" name="ten"><br>
    <label>Mã màu</label><br>
    <input type="radio" name="trangThai" checked value="1"><label>Mở</label><br>
    <input type="radio" name="trangThai" value="2"><label>Tắt</label><br>
    <div>
        <button type="submit">
            Thêm
        </button>
    </div>
</form>
<div>
    Màu vừa thêm:
    <p>${ma}</p>
    <p>${ten}</p>
    <p>${trangThai}</p>
</div>
</body>
</html>
