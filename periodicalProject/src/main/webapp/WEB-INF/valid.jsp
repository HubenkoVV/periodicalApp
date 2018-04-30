<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale" scope="session" value="${not empty sessionScope.locale ? sessionScope.locale : 'en'}" />
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="messages" var="messages" scope="session"/>

<html>
<head>
      	<meta name="viewport" content="width=device-width, initial-scale=1">
  		  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      	<link rel="stylesheet" href="/style/style.css">
  		<script>
			$("document").ready(function() {
			  $(".dropdown-toggle").dropdown();
			});
      $(document).ready(function(){
        ${requestScope.login_modal_open}
        ${requestScope.registration_modal_open}
      });
		</script>
</head>