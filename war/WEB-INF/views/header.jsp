<%-- Fragmento com trecho utilizado no cabecalho das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>


<%
 	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    
    %>


<div id="wrapper-header" class="row-fluid show-grid">
   	<div class="span3">
   		<spring:url var="banner" value="/resources/img/netbizlogo.png" />
		<div class="logo">
			<img src="${banner}" />
		</div>
   	</div>
    <div class="span6">
   		<spring:message code="app.title" var="app_title" htmlEscape="false" />
		<h1 class="title">${app_title}</h1>
    </div>
    
    <div class="well span3">
    <span class="pull-right">
    <spring:url var="icouser" value="/resources/img/ico-user.png" />
    
    <p><img src="${icouser}" />
<%
 if (user != null) {
        pageContext.setAttribute("user", user);
		%>
		
		Hi ${fn:escapeXml(user.nickname)} |
		    <a href="<%= userService.createLogoutURL("http://netbiz-bypass.appspot.com/") %>">sign out</a>
		<%
		} else {
		%>
	
		    <a href="<%= userService.createLoginURL("http://netbiz-bypass.appspot.com/") %>">Sign in</a>
		  
		<%
		    }
		%>
		</p>
   		</span>
    </div>
</div>
