<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<spring:message code="label.sobre.projeto" var="label_sobre_projeto" htmlEscape="false" />
	<spring:message code="label.sobre" var="label_sobre" htmlEscape="false" />
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Access not authorized</h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span12">
			Please, log in at the top of this page. 
		</div>
	</div>

</div>