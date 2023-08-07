<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="css/profile_style.css">
    <meta charset="UTF-8">
    <title>Profile</title>
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
        <input class="upload-button" type="button" value="Upload"/>
    </div>
    <div class="files-list">
        <table>
            <tr>
                <th>File name</th>
                <th>Size</th>
                <th>MIME</th>
            </tr>
            <tr>
                <td><a href="">my_photo.jpeg</a></td>
                <td>16KB</td>
                <td>image/jpg</td>
            </tr>
        </table>
    </div>
</body>
</html>