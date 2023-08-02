<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
                    <tr>
                        <td>December 10, 2020</td>
                        <td>15:34</td>
                        <td>127.0.0.1</td>
                    </tr>
                    <tr>
                        <td>Now!</td>
                        <td>15:34</td>
                        <td>127.0.0.1</td>
                    </tr>
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