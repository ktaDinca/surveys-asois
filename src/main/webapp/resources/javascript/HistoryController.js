angular
    .module('surveys')
    .controller('historyController', ['$scope', '$http', function($scope, $http) {

        $scope.$on('doHistoryTab', function() {
            $scope.getPersonalHistory();
        });

        $scope.getPersonalHistory = function() {
            $http({
                method : 'GET',
                url : '/surveys/history'
            })
            .success(function(data) {
                console.log(data);
                if (data.historySurveys != null) {
                    $scope.history = data.historySurveys;
                }
            });
        };
    }]);