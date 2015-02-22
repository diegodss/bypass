<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %>

<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.mercadoria" var="label_mercadoria" htmlEscape="false" />
<spring:message code="label.mercadoria.nome" var="label_mercadoria_nome" htmlEscape="false" />
<spring:message code="label.mercadoria.descricao" var="label_mercadoria_descricao" htmlEscape="false" />
<spring:message code="label.imagem" var="label_mercadoria_imagem" htmlEscape="false" />
<spring:message code="label.userimagem" var="label_mercadoria_userimagem" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="service" class="form-horizontal" id="frmservice">
	<input type="hidden" name="id" value="${service.id}" />
	<fieldset>
   		<legend><h3>Services<small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">Client</label>
    		<div class="controls">    			
    			<form:errors path="clientid" cssClass="alert alert-error" />
				<form:input path="clientid" class="input-large" id="clientid" />
    		</div>
   		</div>   		
   		
   		<div class="control-group">
    		<label class="control-label">Protocol</label>
    		<div class="controls">
    			<form:input path="protocolid" class="input-large" id="protocolid" />
    			<form:errors path="protocolid" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">URL</label>
    		<div class="controls">
    			<form:input path="url" class="input-large" id="url" />
    			<form:errors path="url" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">App</label>
    		<div class="controls">
    			<form:input path="app" class="input-large" id="app" />
    			<form:errors path="app" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">user</label>
    		<div class="controls">
    			<form:input path="user" class="input-large" id="user" />
    			<form:errors path="user" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">password</label>
    		<div class="controls">
    			<form:input path="password" class="input-large" id="password" />
    			<form:errors path="password" cssClass="alert alert-error" />
    		</div>
   		</div>

   		<div class="control-group">
    		<label class="control-label">Observation</label>
    		<div class="controls">
    			<form:input path="obs" class="input-large" id="obs" />
    			<form:errors path="obs" cssClass="alert alert-error" />
    		</div>
   		</div>

   		
   	</fieldset>
</form:form>


<div class="control-group form-horizontal">
	<div class="controls">
		<button id="salvar" class="btn btn-success">${button_salvar}</button>
		<a href="/"><button class="btn">${button_cancelar}</button></a>
		<c:if test="${not empty param.enableRemove}">
			<button id="excluir" class="btn btn-danger">${button_excluir}</button>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function () {
 	$("#frmservice").validate({
 		 	rules: {
 	 		 	clientid: { required: true },
 	 		 	url: { required: true },
 	 		 	clientid: { required: true},
 	 		 	user: { required: true},
 	 		 	password: { required: true},
 	 		 	
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmservice").submit(); });

});
</script>