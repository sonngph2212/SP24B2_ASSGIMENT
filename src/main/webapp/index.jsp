<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<p>=================</p>
<div style="display: flex; flex-direction: column; gap: 10px">
    <div>
        <a class="btn btn-success" href="hoa-don-ct/index">Hóa đơn chi tiết</a>
    </div>
    <div>
        <a class="btn btn-success" href="hoa-don/index">Hóa đơn</a>
    </div>
    <div>
        <a class="btn btn-success" href="khach-hang/index">Khách hàng</a>
    </div>
    <div>
        <a class="btn btn-success" href="kich-thuoc/index">Kích thước</a>
    </div>
    <div>
        <a class="btn btn-success" href="mau-sac/index">Màu sắc</a>
    </div>
    <div>
        <a class="btn btn-success" href="nhan-vien/index">Nhân viên</a>
    </div>
    <div>
        <a class="btn btn-success" href="san-pham/index">Sản phẩm</a>
    </div>
    <div>
        <a class="btn btn-success" href="san-pham-ct/index">Sản phẩm chi tiết</a>
    </div>
</div>
</body>
</html>