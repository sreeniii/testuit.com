<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="module module-login span4 offset4">
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	<form class="form-vertical"
			action="<c:url value='/j_spring_security_check' />" method='POST'>
		<div class="module-head">
			<h3>Sign In</h3>
		</div>
		<div class="module-body">
			<div class="control-group">
				<div class="controls row-fluid">
					<input class="span12" type="text" id="inputEmail" placeholder="Username">
				</div>
			</div>
			<div class="control-group">
				<div class="controls row-fluid">
					<input class="span12" type="password" id="inputPassword" placeholder="Password">
				</div>
			</div>
		</div>
		<div class="module-foot">
			<div class="control-group">
				<div class="controls clearfix">
					<button type="submit" class="btn btn-primary pull-right">Login</button>
					<label class="checkbox">
						<input type="checkbox"> Remember me
					</label>
				</div>
				<div class="controls clearfix">
					<spring:url value="/forgotPassword" var="forgotPasswordUrl" htmlEscape="true" />
					<a class="pull-right" href="${forgotPasswordUrl}">Forgot your password?</a>
				</div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
	</form>
</div>