<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-moment code="inventor.patronage.form.label.creation-date" path="moment"/>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>

	<%-- The code below is for i18n of the booleans --%>
	<acme:input-select code="authenticated.announcement.form.label.is-critical" path="isCritical">
		<c:if test="${isCritical}">
			<acme:input-option code="authenticated.announcement.form.label.is-critical.true" value="true" selected="true"/>
		</c:if>
		<c:if test="${!isCritical}">
			<acme:input-option code="authenticated.announcement.form.label.is-critical.false" value="false" selected="false"/>
		</c:if>
	</acme:input-select>
	
	<acme:input-url code="authenticated.announcement.form.label.info" path="info"/>
</acme:form>
