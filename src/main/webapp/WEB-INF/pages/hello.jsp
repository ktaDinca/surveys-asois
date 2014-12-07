<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <%--angular--%>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>

    <%--mainController--%>
    <script src="<spring:url value="/resources/javascript/MainController.js"/>"></script>
</head>
<body>
    <div ng-app="surveys">
        <div ng-controller="mainController">
        </div>
    </div>
</body>
</html>