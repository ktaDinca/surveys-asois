angular
    .module('surveys')
    .controller('mainController', ['$scope', '$cookieStore', '$http', function ($scope, $cookieStore, $http) {

        $cookieStore.remove('user');
        $scope.user = $cookieStore.get('user');

        // listen for a 'userLoggedIn' event broadcasted from the child loginController
        $scope.$on('userLoggedIn', function() {
            $scope.user = $cookieStore.get('user');
//            $scope.$broadcast('userLoggedIn2');
        });

        $scope.logoutUser = function() {
            $scope.user = null;
            $cookieStore.remove('user');
            $http({
                method : 'GET',
                url : '/surveys/logout'
            });
        };

        $scope.doHistorySelect = function() {
            $scope.$broadcast('doHistoryTab');
        };

        $scope.doActiveSelect = function() {
            $scope.$broadcast('doActiveTab');
        };

        $scope.doExpiredSelect = function() {
            $scope.$broadcast('doExpiredTab');
        };

        $scope.doLoginHistorySelect = function() {
            $scope.$broadcast('doLoginHistoryTab');
        };

        $scope.doStatisticsSelect= function() {
            $scope.$broadcast('doStatisticsTab');
        };

    }]);