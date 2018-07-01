<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Students Page</title>

    <style type="text/css">

        .hint{
            color: lightgray;
        }

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
<a href="/logout">Logout</a>

<br/>
<br/>

<h1>Student List</h1>

<c:if test="${!empty listStudents}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">First name</th>
            <th width="120">Last name</th>
            <th width="120">Birth date</th>
            <th width="60">Email</th>
            <th width="60">Phone</th>
            <th width="60">Knowledge</th>
            <th width="60">English level</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listStudents}" var="student">
            <tr>
                <td>${student.id}</td>
                <td><a href="/get/${student.id}" target="_blank">${student.firstName}</a></td>
                <td>${student.lastName}</td>
                <td>${student.birthDate}</td>
                <td>${student.email}</td>
                <td>${student.phoneNumber}</td>
                <td>${student.knowledge}</td>
                <td>${student.englishLevel}</td>
                <td><a href="<c:url value='/update/${student.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${student.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Student</h1>

<c:url var="addAction" value="/students/add"/>
<form:form action="${addAction}" commandName="student">
    <table>
        <c:if test="${!empty student.firstName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="First name"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="Last name"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birthDate">
                    <spring:message text="Birth date"/>
                </form:label>
            </td>
            <td>
                <form:input path="birthDate"/> <label><span class="hint">yyyy/mm/dd</span></label>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="phoneNumber">
                    <spring:message text="Phone"/>
                </form:label>
            </td>
            <td>
                <form:input path="phoneNumber"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="knowledge">
                    <spring:message text="Knowledge"/>
                </form:label>
            </td>
            <td>
                <form:input path="knowledge"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="englishLevel">
                    <spring:message text="English level"/>
                </form:label>
            </td>
            <td>
                <form:input path="englishLevel"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty student.firstName}">
                    <input type="submit"
                           value="<spring:message text="Edit student"/>"/>
                </c:if>
                <c:if test="${empty student.firstName}">
                    <input type="submit"
                           value="<spring:message text="Add student"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>