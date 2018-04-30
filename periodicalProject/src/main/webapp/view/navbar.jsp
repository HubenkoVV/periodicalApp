<%@ include file="/WEB-INF/valid.jsp" %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/api/periodicals">NEWS</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/api/periodicals"><fmt:message bundle="${messages}" key="periodicals"/></a></li>
      <li><a href="${pageContext.request.contextPath}/api/articles"><fmt:message bundle="${messages}" key="articles"/></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li class = "dropdown ${requestScope.dropdown_open}">
      				<a href="#" data-toggle="dropdown" class = "dropdown-toggle">
      				    <c:if test = "${sessionScope.periodicals_in_basket ne null}">
                    <li> <span class="badge progress-bar-danger">${sessionScope.periodicals_in_basket.size()}</span></li>
                  </c:if>
      					<span class="glyphicon glyphicon-shopping-cart"></span>
      				</a>
      				<ul class = "dropdown-menu" id = "dropdown" style = "min-width:250; padding-right:5px; padding-left:5px;">
      					<li class="bg-primary text-info text-center"><fmt:message key = "basket" bundle = "${messages}"/> </li>
      					<li class="divider"></li>
      					<c:if test = "${sessionScope.periodicals_in_basket.length() == 0}">
      					<li>
      						<fmt:message key = "empty.basket" bundle = "${messages}"/>
      					</li>
      					</c:if>
      					<c:forEach items = "${sessionScope.periodicals_in_basket}" var = "item">
      						<li>
      							<form action = "/api/delete_from_basket" method = "POST" class="form-horizontal">
      								<span><c:out value = "${item.name}"/></span>
      								<input type = "hidden" value = "${item.id}" name = "periodical_id">

      								<button type="submit" class="btn btn-xs btn-danger pull-right" aria-label="Close" name = "command" value = "delete_from_basket"><span aria-hidden="true">&times;</span></button>
      								<span class = "pull-right">
      									<c:set var = "price" target = "item" property = "price" value = "${item.price/100}"/>
      									<c:out value = "${price}"/>
      									&nbsp;
      								</span>
      							</form>
      						</li>
      					</c:forEach>
      					<li class="divider"></li>
      					<li>
      						<div class = "row" style = "margin-right:0px; margin-lef:0px">
      							<span class = "pull-right bg-info">
      							<fmt:message key = "total.price" bundle = "${messages}"/>
      							<c:set var = "total_price" target = "sessionScope.full_price" value = "${sessionScope.full_price/100}"/>
      							<c:out value = "${total_price}"/>
      							</span>
      						</div>
      					</li>
      					<li class="divider"></li>
      					<li>
      						<form action = "/api/subscribe" method = "POST" class="form-horizontal" style = "margin-bottom:0;">
      							<button type = "submit" class = "btn btn-success center-block"><fmt:message key = "subscribe" bundle = "${messages}"/></button>
      						</form>
      					</li>
      				</ul>
          </li>
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key = "language" bundle = "${messages}"/> <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="/api/language?locale=en"><fmt:message bundle="${messages}" key="en"/></a></li>
              <li><a href="/api/language?locale=uk"><fmt:message bundle="${messages}" key="ua"/></a></li>
            </ul>
          </li>

      <c:if test="${sessionScope.user_role.name eq 'GUEST'}">
        <li><a href="#" data-toggle="modal" data-target="#login_modal"><span class="glyphicon glyphicon-user"></span> <fmt:message bundle="${messages}" key="signIn"/></a></li>
        <li><a href="#" data-toggle="modal" data-target="#registration_modal"><span class="glyphicon glyphicon-log-in"></span> <fmt:message bundle="${messages}" key="registration"/></a></li>
      </c:if>
      <c:if test="${sessionScope.user_role.name ne 'GUEST'}">
        <p class="navbar-text" style="color: white;">${sessionScope.user.surname} ${sessionScope.user.name}</p>
        <li><a href="#"><span class="glyphicon glyphicon-sunglasses"></span> <fmt:message bundle="${messages}" key="account"/></a></li>
        <li><a href="${pageContext.request.contextPath}/api/sign_out"><span class="glyphicon glyphicon-log-out"></span> <fmt:message bundle="${messages}" key="signOut"/></a></li>
      </c:if>
    </ul>
  </div>
</nav>

      <div class="modal fade" id="login_modal" role="dialog">
          <div class="modal-dialog modal-sm">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3><fmt:message bundle="${messages}" key="signIn"/></h3>
              </div>
              <div class="modal-body">
                <c:if test = "${requestScope.exception ne null}">
                  <div class="alert alert-danger alert-dismissible center-block" style="width: 90%;">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                    ${requestScope.exception}
                  </div>  
                </c:if>
                <form action="/api/login" method = "POST">
                  <h4><fmt:message bundle="${messages}" key="login"/></h4>
                  <input class="form-control" type="text" name="loginSignIn" id="loginSignIn" placeholder= "<fmt:message bundle="${messages}" key="login"/>" required>
                  <h4><fmt:message bundle="${messages}" key="password"/></h4>
                  <input class="form-control" type="password" name="passwordSignIn" id="passwordSignIn" placeholder= "<fmt:message bundle="${messages}" key="password"/>" required>
                  <div class="text-center"><button class="btn btn-success" style="border-color: black; background-color: #5c5c5c; margin: 10px;" id="signInButton" type="submit"> <fmt:message bundle="${messages}" key="signIn"/></button></div>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message bundle="${messages}" key="back"/></button>
              </div>
            </div>
          </div>
      </div>

      <div class="modal fade" id="registration_modal" role="dialog">
          <div class="modal-dialog modal-sm">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3><fmt:message bundle="${messages}" key="registration"/></h3>
              </div>
              <div class="modal-body">
                <c:if test = "${requestScope.exception ne null}">
                  <div class="alert alert-danger alert-dismissible center-block" style="width: 90%;">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                    ${requestScope.exception}
                  </div>  
                </c:if>
                <form action="/api/registration" method = "POST">
                  <h4><fmt:message bundle="${messages}" key="login"/></h4>
                  <input class="form-control" type="text" name="login" id="login" placeholder= "<fmt:message bundle="${messages}" key="login"/>" required>
                  <h4><fmt:message bundle="${messages}" key="password"/></h4>
                  <input class="form-control" type="password" name="password" id="password" placeholder= "<fmt:message bundle="${messages}" key="password"/>" required>
                  <h4><fmt:message bundle="${messages}" key="passwordAgain"/></h4>
                  <input class="form-control" type="password" name="passwordAgain" id="passwordAgain" placeholder= "<fmt:message bundle="${messages}" key="password"/>" required>
                  <h4><fmt:message bundle="${messages}" key="name"/></h4>
                  <input class="form-control" type="text" name="name" id="name" placeholder= "<fmt:message bundle="${messages}" key="name"/>" required>
                  <h4><fmt:message bundle="${messages}" key="surname"/></h4>
                  <input class="form-control" type="text" name="surname" id="surname" placeholder= "<fmt:message bundle="${messages}" key="surname"/>" required>
                  <h4><fmt:message bundle="${messages}" key="phone"/></h4>
                  <input class="form-control" type="text" name="phone" id="phone" placeholder= "<fmt:message bundle="${messages}" key="phone.format"/>">
                  <div class="text-center"><button class="btn btn-success" style="border-color: black; background-color: #5c5c5c; margin: 10px;" id="registrationButton" type="submit"> <fmt:message bundle="${messages}" key="registrate"/></button></div>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message bundle="${messages}" key="back"/></button>
              </div>
            </div>
          </div>
      </div>