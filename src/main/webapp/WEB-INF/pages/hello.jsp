<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
    <head>
        <%--jQuery--%>
        <script src="<spring:url value="/resources/javascript/vendor/jquery.js"/>"></script>

        <%--angular--%>
        <script src="<spring:url value="/resources/javascript/vendor/angular.js"/>"></script>
        <%--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>--%>

        <%--bootstrap--%>
        <script src="<spring:url value="/resources/javascript/vendor/bootstrap.js"/>"></script>
        <link rel="stylesheet" href="<spring:url value="/resources/css/vendor/bootstrap.css" />"/>

        <%--controllers--%>
        <script src="<spring:url value="/resources/javascript/MainController.js"/>"></script>
    </head>
    <body>
        <div ng-app="surveys">
            <div ng-controller="mainController">
                <input type="text" ng-model="ceva">
                <button type="button" class="btn btn-danger">{{ceva}}</button>
            </div>
        </div>
    </body>
</html>