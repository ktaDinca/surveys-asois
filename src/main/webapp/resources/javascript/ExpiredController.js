angular
    .module('surveys')
    .controller('expiredController', ['$scope', '$http', function($scope, $http) {

        $scope.$on('doExpiredTab', function() {
            $scope.getExpiredSurveys();
        });

        $scope.surveys = [];

        $scope.getExpiredSurveys = function() {
            $http({
                method : 'GET',
                url : '/surveys/expired'
            }).success(function(data) {
                console.log(data);
                if (data.surveys != null) {
                    $scope.surveys = data.surveys;
                }
            });
        };

    }]);