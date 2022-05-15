<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.status" path="status" width="10%"/>
	<acme:list-column code="patron.patronage.list.label.code" path="code"/>
	<acme:list-column code="patron.patronage.list.label.budget" path="budget"/>
	<acme:list-column code="patron.patronage.list.label.exchange" path="exchange"/>
	<acme:list-column code="patron.patronage.list.label.start-date" path="startDate"/>
	<acme:list-column code="patron.patronage.list.label.end-date" path="endDate"/>
	<acme:list-column code="inventor.item.list.label.published" path="published" width="10%"/>
</acme:list>
