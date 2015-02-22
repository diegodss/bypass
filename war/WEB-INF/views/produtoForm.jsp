<%-- 
     Fragmento com o formulario de preenchimento com os dados da mercadoria.
     Utilizado pela pagina de inclusao e edicao de mercadoria.
     O formulario de mercadorias utiliza o plugin Validation do JQuery, para validar os inputs.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:message code="button.salvar" var="button_salvar" htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar" htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir" htmlEscape="false" />

<spring:message code="label.mercadoria" var="label_mercadoria" htmlEscape="false" />
<spring:message code="label.mercadoria.nome" var="label_mercadoria_nome" htmlEscape="false" />
<spring:message code="label.mercadoria.descricao" var="label_mercadoria_descricao" htmlEscape="false" />
<spring:message code="label.imagem" var="label_mercadoria_imagem" htmlEscape="false" />
<spring:message code="label.userimagem" var="label_mercadoria_userimagem" htmlEscape="false" />

<form:form action="" method="${param.method}" modelAttribute="produto" class="form-horizontal" id="frmProduto">
	<input type="hidden" name="id" value="${produto.id}" />
	<fieldset>
   		<legend><h3>${label_mercadoria} <small> ${param.sublabel}</small></h3></legend>
   		<div class="control-group">
    		<label class="control-label">${label_mercadoria_nome}</label>
    		<div class="controls">
    			<form:input path="nome" class="input-large" />
    			<form:errors path="nome" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_mercadoria_descricao}</label>
    		<div class="controls">
    			<form:input path="descricao" class="input-large"/>
    			<form:errors path="descricao" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_mercadoria_imagem}</label>
    		<div class="controls">
    			<form:input path="imagem" class="input-small" id="imagem" />
    			<form:errors path="imagem" cssClass="alert alert-error" />
    		</div>
   		</div>
   		
   		<div class="control-group">
    		<label class="control-label">${label_mercadoria_userimagem}</label>
    		<div class="controls">
    			<form:input path="userimagem" class="input-small"/>
    			<form:errors path="userimagem" cssClass="alert alert-error" />
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
 	$("#frmProduto").validate({
 		 	rules: {
 	 		 	nome: { required: true, minlength: 5 },
 	 		 	imagem: { required: true}
 		 	}
 	});
 	
 	$("#salvar").click(function () { $("#frmProduto").submit(); });

});
</script>