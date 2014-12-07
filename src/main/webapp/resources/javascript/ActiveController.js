angular
    .module('surveys')
    .controller('activeController', ['$scope', '$cookieStore', function($scope, $cookieStore) {

//        $scope.$on('userLoggedIn2', function() {
//            $scope.user = $cookieStore.get('user');
//        });

        $scope.newSurvey = $scope.newSurvey || {};

        clearAddSurveyBox = function() {
            $scope.newSurvey = null;
            $scope.tempAnswer = '';
            $scope.tempDescription = '';
        };

        $scope.addSurvey = function() {
            $scope.newSurvey.description = $scope.tempDescription;
            $scope.surveys.push($scope.newSurvey);

            clearAddSurveyBox();
        };

        $scope.addNewAnswerToSurvey = function() {
            $scope.newSurvey.answers = $scope.newSurvey.answers || [];
            $scope.newSurvey.answers.push($scope.tempAnswer);
        };

        $scope.surveys = [
            {
                id:1,
                description:'survey1',
                answers:['ans1', 'ans2', 'ans3'],
                status:'active'
            },
            {
                id:2,
                description:'survey2',
                answers:['ans1', 'ans2', 'ans3'],
                status:'active'
            }
        ];

        $scope.attemptVote = function() {
            console.log("TODO: vote");
        }

    }]);
