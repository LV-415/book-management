<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/book" method="post">
    <input type="hidden" name="action" value="update" id="actionForm">
    <table>
        <input type="hidden" name="id" value="${sua.id}">
        <tr>
            <td><label>Ma</label></td>
            <td><input type="text" name="ma" required value="${sua.ma}"></td>
        </tr>
        <tr>
            <td><label>ten</label></td>
            <td><input type="text" name="ten" required value="${sua.ten}"></td>
        </tr>
        <tr>
            <td><label>so trang</label></td>
            <td><input type="number" name="soTrang" required value="${sua.soTrang}" min="1" max="1500"></td>
        </tr>
        <tr>
            <td><label>Don gia</label></td>
            <td><input type="number" name="donGia" required value="${sua.donGia}" min="1" max="150000000"></td>
        </tr>
    </table>
    <button type="submit" onclick="setActionUpdate()">Update</button>
    <button type="submit" onclick="setActionAdd()">Add</button>
</form>

<table border="1">
    <thead>
    <th>STT</th>
    <th>Ma</th>
    <th>Ten</th>
    <th>So trang</th>
    <th>Don gia</th>
    <th colspan="2">Hanh dong</th>
    </thead>
    <tbody>
    <c:forEach var="u" items="${list}" varStatus="v">
        <tr>
            <td>${v.index+1}</td>
            <td>${u.ma}</td>
            <td>${u.ten}</td>
            <td>${u.soTrang}</td>
            <td>${u.donGia}</td>
            <td><a href="/book?action=detail&&id=${u.id}">
                <button>detail</button>
            </a>
            </td>
            <td>
                <button type="button" onclick="xoa('${u.id}')">Xoa</button>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
    function xoa(id) {
        cf = confirm("Ban co muon xoa khong");
        if (cf) {
            location.href = "/book?action=del&&id=" + id;
        }
    }

    function setActionUpdate() {
        document.getElementById("actionForm").value = "update";
    }

    function setActionAdd() {
        document.getElementById("actionForm").value = "add";
    }

</script>
</html>
