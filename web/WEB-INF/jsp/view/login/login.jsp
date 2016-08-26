<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 24/08/2016
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template:app htmlTitle="Login" bodyTitle="Pagina de login" >
    <jsp:body>
        <h1>Login</h1>
        <form:form modelAttribute="userDto" action="/login" method="post" cssClass="col s12" autocomplete="off">
            <security:csrfInput/>
            <div class="row">
                <div class="input-field col s12">
                    <form:input path="username" type="text" class="validate" autocomplete="off"/>
                    <form:label path="username" for="username">User Name</form:label>
                    <form:label path="username">
                        <form:errors path="username"/>
                    </form:label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:input path="password" type="password" class="validate" autocomplete="off"/>
                    <form:label path="password" for="password">Password</form:label>
                    <form:label path="password">
                        <form:errors path="password"/>
                    </form:label>
                </div>
            </div>
            <input type="submit" value="Send" class="waves-effect btn">
        </form:form>
    </jsp:body>
</template:app>