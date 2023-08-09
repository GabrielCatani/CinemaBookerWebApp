<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="css/profile_style.css">
    <meta charset="UTF-8">
    <title>Profile</title>
    <script src="<c:url value='/js/script.js'/>" type="text/javascript"></script>
</head>
<body>
    <title>User Profile</title>
    <div class="user-personal-info">
        <img id="userAvatar" src="images/icon-image-not-found-free-vector.jpg"/>
        <div class="hi-and-logged">
            <h1>It`s Me </br>
                ${sessionScope.userEmail}
            </h1>
            <div class="logged-table">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>IP</th>
                    </tr>
                    <c:forEach var="usrLog" items="${logList}" varStatus="loop">
                    <tr>
                        <td>${usrLog.getMonth()} ${usrLog.getDayOfMonth()}, ${usrLog.getYear()} </td>
                        <td>${usrLog.getHour()}:${usrLog.getMinute()}</td>
                        <td>${usrLog.IP}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="up-button">
        <input class="upload-button" id="fileInput" type="file" style="display: none"/>
        <button onclick="chooseFile()">Upload</button>
    </div>
    <div class="files-list">
        <table>
            <tr>
                <th>File name</th>
                <th>Size</th>
                <th>MIME</th>
            </tr>
            <c:forEach var="usrImg" items="${imgList}" varStatus="loop">
            <tr>
                <td><a href="/CinemaBookingWebApp/images/${usrImg.getFileName()}">${usrImg.getFileName()}</a></td>
                <td>${usrImg.getFileSize()}</td>
                <td>${usrImg.getMimeType()}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>