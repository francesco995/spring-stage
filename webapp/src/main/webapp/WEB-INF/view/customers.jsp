<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
        <style>
            body {
                padding-top: 50px;
                padding-bottom: 20px;
            }
        </style>
        
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">

        <script src="<c:url value="/resources/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	    	<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Project name</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<form class="navbar-form navbar-right" role="form">
						<div class="form-group">
							<input type="text" placeholder="Email" class="form-control">
						</div>
						<div class="form-group">
							<input type="password" placeholder="Password" class="form-control">
						</div>
						<button type="submit" class="btn btn-success">Sign in</button>
					</form>
				</div>
				<!--/.navbar-collapse -->
			</div>
	    </nav>
	
	    <!-- Main jumbotron for a primary marketing message or call to action -->
	    <div class="jumbotron">
	    	<div class="container">
			  <h1>Hello, world!</h1>
			  <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
			  <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
			</div>
	    </div>
	    
	
	    <div class="container">
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
				  			<spring:message code="customers.details.phones" />:<c:forEach items="${customer.phones}" var="phone"><c:out value="${phone}"/>,&nbsp;</c:forEach>
				  		</p>
				  	</div>
				  </div>
				</c:forEach>
			</div>
			<hr>
			
			<footer>
				<p>&copy; Company 2015</p>
			</footer>
	    </div> <!-- /container -->        
	    
		<script src="<c:url value="/resources/js/vendor/jquery-1.11.2.min.js" />"></script>
		<script src="<c:url value="/resources/js/vendor/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/main.js" />"></script>
    </body>
</html>
