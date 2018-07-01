<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>StudentData</title>

    <style type="text/css">
        .tg {
        border-collapse: collapse;
        border-spacing: 0;
        border-color: #ccc;
        }

        .tg td {
        font-family: Arial, sans-serif;
        font-size: 14px;
        padding: 10px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        color: #333;
        background-color: #fff;
        }

        .tg th {
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        padding: 10px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        color: #333;
        background-color: #f0f0f0;
        }

        .tg .tg-4eph {
        background-color: #f9f9f9
        }
    </style>

</head>
<body>
    <h1>Student Details</h1>

    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">First name</th>
            <th width="120">Last name</th>
            <th width="60">Birth date</th>
            <th width="120">Email</th>
            <th width="120">Phone</th>
            <th width="120">Knowledge</th>
            <th width="120">English level</th>
        </tr>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.birthDate}</td>
            <td>${student.email}${student.email}</td>
            <td>${student.phoneNumber}</td>
            <td>${student.knowledge}</td>
            <td>${student.englishLevel}</td>
        </tr>
    </table>
</body>
</html>