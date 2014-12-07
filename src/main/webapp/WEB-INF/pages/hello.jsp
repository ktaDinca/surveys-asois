<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
    <head>
        <%--jQuery--%>
        <script src="<spring:url value="/resources/javascript/vendor/jquery.js"/>"></script>

        <%--angular--%>
        <script src="<spring:url value="/resources/javascript/vendor/angular.js"/>"></script>
        <script src="<spring:url value="/resources/javascript/vendor/angular-route.js"/>"></script>
        <script src="<spring:url value="/resources/javascript/vendor/angular-cookies.js"/>"></script>

        <%--bootstrap--%>
        <%--<script src="<spring:url value="/resources/javascript/vendor/bootstrap.js"/>"></script>--%>
        <script src="<spring:url value="/resources/javascript/vendor/ng-bootstrap.min.js"/>"></script>
        <link rel="stylesheet" href="<spring:url value="/resources/css/vendor/bootstrap.css" />"/>

        <%--controllers--%>
        <script src="<spring:url value="/resources/javascript/ConfigController.js"/>"></script>
        <script src="<spring:url value="/resources/javascript/MainController.js"/>"></script>
        <script src="<spring:url value="/resources/javascript/ActiveController.js"/>"></script>
        <script src="<spring:url value="/resources/javascript/LoginController.js"/>"></script>
    </head>
    <body>
        <div ng-app="surveys">
            <div ng-controller="configController">
                <div ng-view></div>
            </div>
        </div>
    </body>
</html>