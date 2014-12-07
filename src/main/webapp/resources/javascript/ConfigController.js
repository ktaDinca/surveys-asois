angular
    .module('surveys', ['ngRoute', 'ui.bootstrap', 'ngCookies'])
    .config(function ($routeProvider) {
        var baseUrl = '/surveys/resources/html/';
        $routeProvider
            .when('/', {
                templateUrl:baseUrl + 'landing.html'
            })
    })
    .controller('configController', ['$scope', function($scope) {

    }]);