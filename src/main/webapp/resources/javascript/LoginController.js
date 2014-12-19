angular
    .module('surveys')
    .controller('loginController', ['$scope', '$cookieStore', '$http', function ($scope, $cookieStore, $http) {
        $scope.showLoginCredentialBox = false;
        $scope.attemptLogin = function (credentialsEntered) {

            if (!credentialsEntered) {
                $scope.showLoginCredentialBox = true;
                return;
            }

            $http({
                method: 'POST',
                url: '/surveys/login',
                params: {
                    username: $scope.username,
                    password: $scope.password
                }
            })
            .success(function (data) {
                if (data.user != null) {
                    // save the user in a cookie
                    $cookieStore.put('user', data.user);

                    // notify parent $scope (MainController) that the user
                    // has logged in, not to display the login box any more
                    $scope.$emit('userLoggedIn');
                } else {
                    alert("Login credentials are invalid!");
                }
            });
        }
    }]);