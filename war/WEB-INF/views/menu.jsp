<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<spring:message code="label.mercadorias" var="label_mercadorias" htmlEscape="false" />
	<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
	<spring:message code="menu.incluir" var="menu_incluir" htmlEscape="false" />
	<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />
	
	<ul class="nav nav-list" style="padding-top: 15px;">
		<li class="${empty active || active == 'clientlista' ? 'active' : ''}">
			<a href="/client">Client</a>
		</li>
		<li class="${active == 'clientincluir' ? 'active' : ''}">
			<a href="/client?form">Add Client</a>
        </li>        
<li class="divider"></li>
		<li class="${active == 'protocollista' ? 'active' : ''}">
			<a href="/protocol">Protocols</a>
		</li>
		<li class="${active == 'protocolincluir' ? 'active' : ''}">
			<a href="/protocol?form">Add Protocol</a>
        </li>        
<li class="divider"></li>
		<li class="${active == 'servicelista' ? 'active' : ''}">
			<a href="/service">Services</a>
		</li>
		<li class="${active == 'serviceincluir' ? 'active' : ''}">
			<a href="/?form">Add Service</a>
        </li>        



		<li class="divider"></li>
		<li class="${active == 'sobre' ? 'active' : ''}">
			<a href="/sobre">${menu_sobre}</a>
		</li>
	</ul>
</div>
