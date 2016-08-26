<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 24/08/2016
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jsp"%>
<template:app htmlTitle="App" bodyTitle="App">
    <jsp:body>
        <h1>Entro en App</h1>
        <security:authorize access="hasAuthority('ADMIN_WEB')">
            <h4>Admin Web</h4>
            <a href="/app/admin" >Admin</a>
        </security:authorize>

        <security:authorize access="hasAuthority('USER_VIEW')">
            <h4>User View</h4>
        </security:authorize>
    </jsp:body>
</template:app>
