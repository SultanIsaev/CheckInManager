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
	<h2><a href="/admin">Admin_panel</a></h2>
		<br/>
		<br/>

		<h1>Add a Student</h1>

		<c:url var="addAction" value="/register"/>
		<form:form action="${addAction}" commandName="student">
			<table>
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
						<input type="submit"
							   value="<spring:message text="Add student"/>"/>
					</td>
				</tr>
			</table>
		</form:form>

		<br/>
	</body>
</html>