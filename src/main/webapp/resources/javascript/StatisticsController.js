angular
    .module('surveys')
    .controller('statisticsController', ['$scope', '$http', function ($scope, $http) {

        $scope.$on('doStatisticsTab', function() {
            $scope.getStatsData();
        });

        $scope.getStatsData = function() {

            $http({
                method : 'GET',
                url : '/surveys/stats'
            })
            .success(function(data) {
                console.log(data);
                if (data.stats != null) {
                    $scope.statistics = data.stats;
                }
                else {
                    alert("No votes have been filled in!");
                    return;
                }
            });
        };

    }]);