angular
    .module('surveys')
    .controller('registerController', ['$scope', '$http', '$cookieStore', function($scope, $http, $cookieStore) {
        $scope.registerButtonPressed = false;

        $scope.attemptRegister = function(registerButtonPressed) {
            $scope.registerButtonPressed = !$scope.registerButtonPressed;
            if (!registerButtonPressed) {
                return;
            }

            if ($scope.reg_username == null || $scope.reg_username == '') {
                alert("Please fill in your username!");
                return;
            }

            if ($scope.reg_password == null || $scope.reg_password == '') {
                alert("Please fill in your password!");
                return;
            }

            if ($scope.reg_country == null || $scope.reg_country == '') {
                alert("Please fill in your country!");
                return;
            }

            $http({
                method : 'POST',
                url : '/surveys/register',
                params : {
                    username : $scope.reg_username,
                    password : $scope.reg_password,
                    country : $scope.reg_country
                }
            }).success(function(data) {
                if (data.user != null) {
                    // save the user in a cookie
                    $cookieStore.put('user', data.user);

                    // notify parent $scope (MainController) that the user
                    // has logged in, not to display the login box any more
                    $scope.$emit('userLoggedIn');
                }
            });

        }
    }]);
