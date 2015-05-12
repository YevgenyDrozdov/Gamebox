<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>List of games</title>
        <link rel="stylesheet" href="<c:url value='/styles/gamebox.css' />" />
        <script src="<c:url value='/js/lib/underscore-min.js' />"></script>
        <script src="<c:url value='/js/lib/jquery-2.1.3.min.js' />"></script>
        <script src="<c:url value='/js/lib/prototype.js' />"></script>

        <script src="<c:url value='/js/Component.js' />"></script>
        <script src="<c:url value='/js/gameModel.js' />"></script>
        <script src="<c:url value='/js/gameComponent.js' />"></script>
        <script src="<c:url value='/js/gameListModel.js' />"></script>
        <script src="<c:url value='/js/gameListComponent.js' />"></script>
    </head>

    <body>

        <script type="text/javascript" charset="utf-8">
            //var gameListModel = new GameListModel();

            var gameListComponent = new GameListComponent("/rest/games");
            gameListComponent.setId("game-list");
            jQuery("body").append(gameListComponent.getContainer());

        </script>

         <%--<div id="game-list">
        <c:forEach items="${games}" var="gameEntry">
            <script language="javascript">
                new GameModel('<c:out value="${gameEntry.key}" />', '/rest/<c:out value="${gameEntry.key}" />/loadModel');
            </script>

            <div class="game-view-container" style="background-image: url('<c:url value='/rest/miniature/${gameEntry.key}' />')">
                <div class="game-description">
                    <ul>
                        <li>Id: <b><c:out value="${gameEntry.key}" /></b></li>
                        <li>Name: <c:out value="${gameEntry.value.descriptor.gameName}" /></li>
                        <li>Version: <c:out value="${gameEntry.value.descriptor.gameVersion}" /></li>
                        <li>Controller class: <c:out value="${gameEntry.value.descriptor.controllerClass}" /></li>
                        <li>Miniature path: <c:out value="${gameEntry.value.descriptor.miniaturePath}" /></li>
                    </ul>
                </div>
                <div class="start-button"></div>
            </div>
        </c:forEach>
        </div>--%>



    </body>
</html>