angular
    .module('surveys', [])
    .controller('mainController', ['$scope', function ($scope) {
        console.log("initialized!");
        $scope.name = 'World';
    }]);