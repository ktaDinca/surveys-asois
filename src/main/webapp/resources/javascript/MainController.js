angular
    .module('surveys')
    .controller('mainController', ['$scope', '$cookieStore', function ($scope, $cookieStore) {

        $cookieStore.remove('user');
        $scope.user = $cookieStore.get('user');

        // listen for a 'userLoggedIn' event broadcasted from the child loginController
        $scope.$on('userLoggedIn', function() {
            $scope.user = $cookieStore.get('user');
//            $scope.$broadcast('userLoggedIn2');
        });
    }]);