<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
				<i class="icon-reorder shaded"></i>
			</a>

		  	<a class="brand" href="#">
		  		Testuit
		  	</a>

			<div class="nav-collapse collapse navbar-inverse-collapse">
			
				<ul class="nav pull-right">

					<!-- <li><a href="#">
						Sign Up
					</a></li> -->
					
					<li>
						<spring:url value="/login" var="loginUrl" htmlEscape="true" />
						<a href="${loginUrl}">Sign In</a>
					</li>
					<!-- <li>
						<spring:url value="/forgotPassword" var="forgotPasswordUrl" htmlEscape="true" />
						<a href="${forgotPasswordUrl}">Forgot your password?</a>
					</li> -->
				</ul>
			</div><!-- /.nav-collapse -->
		</div>
	</div><!-- /navbar-inner -->
</div>