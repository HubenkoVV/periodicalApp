<%@ include file="/WEB-INF/valid.jsp" %>
<body>
    <%@ include file="navbar.jsp" %>
    <div class="container">
	    <h2><fmt:message bundle="${messages}" key="periodical"/></h2>
	    <c:forEach items = "${requestScope.periodicals_list}" var = "periodical_item">
			<h3 style = "text-align: left"><c:out value = "${periodical_item.name}"/></h3>
			<div><c:out value = "${periodical_item.shortDescription}"/></div>
			<div>
				<c:if test = "${not user_periodicals.contains(periodical_item)}">
						<form action = "/api/add_to_basket" method = "POST">
							<span class = "text-warning lead"><fmt:message key = "subscribe.now" bundle = "${messages}"/>
								<c:out value = "${periodical_item.price}"/>
							</span>
							<input type = "hidden" value = "${periodical_item.id}" name = "id_periodical">
							<button type="submit" class="btn btn-info pull-right"><fmt:message key = "subscribe" bundle = "${messages}"/></button>
						</form>
				</c:if>
			</div>
			<hr/>
		</c:forEach>
		<div class = "text-center">
			<ul class = "pagination">
				<c:forEach items = "${requestScope.pages_of_periodicals}" var = "page">
					<c:choose>
						<c:when test = "${page == requestScope.current_page}">
							<li class="active"><a href="/api/periodicals?periodicals_page=${page}">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/api/periodicals?periodicals_page=${page}">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>