/**
 * Created by ktaDinca on 12/19/2014.
 */

angular
    .module('surveys')
    .controller('logHistoryController', ['$scope', '$http', function($scope, $http) {

        $scope.logs = [];

        $scope.loadLogs = function() {
            $http({
                method: 'GET',
                url: '/surveys/logs'
            })
            .success(function (data) {
                console.log(data);
                if (data.logs != null) {
                    $scope.logs = data.logs;
                }
            });
        };

        $scope.loadLogs();


    }]);