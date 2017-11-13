<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="row">
  	<c:forEach items="${customers}" var="customer">
	  <div class="panel panel-primary">
	  	<div class="panel-heading">
	  		<c:out value="${customer.name}" />&nbsp;<c:out value="${customer.lastName}" />
	  	</div>
	  	<div class="panel-body">
	  		<p><spring:message code="customers.details.age" />: <c:out value="${customer.age}" /></p>
	  		<spring:message code="customers.details.birthdate.pattern" var="pattern" />
	  		<p><spring:message code="customers.details.birthdate" />: <fmt:formatDate value="${customer.birthDate}" pattern="${pattern}" /> </p>
	  		<p>
	  			<spring:message code="customers.details.phones" />:<c:forEach items="${customer.phones}" var="phone" varStatus="status"><c:out value="${phone}"/><c:if test="${!status.last}">,&nbsp;</c:if></c:forEach>
	  		</p>
	  	</div>
	  </div>
	</c:forEach>
</div>