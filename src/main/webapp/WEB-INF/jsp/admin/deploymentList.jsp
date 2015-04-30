<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>List of deployments</title>
        <link rel="stylesheet" href="<c:url value='/styles/gamebox.css' />" />
        <script src="<c:url value='/js/lib/underscore-min.js' />"></script>
        <script src="<c:url value='/js/lib/jquery-2.1.3.min.js' />"></script>
    </head>

    <body>

        <table class="deployments">
        <c:forEach items="${deployments}" var="deployment">
            <tr>
                <td><c:out value="${deployment.deploymentId.id}" /></td>
                <td><c:out value="${deployment.deploymentTime}" /></td>
                <td><c:out value="${deployment.classLoader.getClass().name}" /></td>
            </tr>
        </c:forEach>
        </table>
        <a href="<c:url value='/admin/refresh-deployments' />">Refresh deployments</a>

    </body>
</html>