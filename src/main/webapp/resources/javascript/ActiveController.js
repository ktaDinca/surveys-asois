angular
    .module('surveys')
    .controller('activeController', ['$scope', '$cookieStore', '$http', function($scope, $cookieStore, $http) {

        $scope.$on('doActiveTab', function() {
            $scope.surveys = $scope.getActiveSurveys() || [] ;
        });

//        $scope.$on('userLoggedIn2', function() {
//            $scope.user = $cookieStore.get('user');
//        });

        $scope.tempId = null;
        $scope.dateFormat = 'dd-MMMM-yyyy';
        $scope.minDate = new Date();

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.datePickerOpened = true;
        };

        $scope.getActiveSurveys = function() {
            $http({
                method : 'GET',
                url : '/surveys/active'
            }).success(function(data) {
                console.log(data);
                $scope.surveys = data.surveys || [];
                return data.surveys;
            });
        };

        $scope.surveys = $scope.getActiveSurveys() || [] ;
        $scope.newSurvey = {answers: []};

        clearAddSurveyBox = function() {
            $scope.newSurvey = {answers: []};
            $scope.tempAnswer = '';
            $scope.tempDescription = '';
        };

        $scope.addSurvey = function() {
            if ($scope.tempDescription == null || $scope.tempDescription == '') {
                alert("Survey description could not be empty!");
                return ;
            }

            console.log($scope.newSurvey.answers);

            if ($scope.newSurvey.answers == null || $scope.newSurvey.answers.length < 1) {
                alert("There must be at least 2 answers!");
                return ;
            }

            if ($scope.tempExpireDate == null) {
                alert("Please select the expiration date!");
                return ;
            }

            $scope.newSurvey.description = $scope.tempDescription;

            console.log($scope.tempExpireDate.getTime());

            // save to db
            $http({
                method: 'POST',
                url : '/surveys/save',
                params : {
                    description : $scope.newSurvey.description,
                    expDate : $scope.tempExpireDate.getTime(),
                    answers : $scope.newSurvey.answers
                }
            }).success(function(data) {
                $scope.getActiveSurveys();
            });

            clearAddSurveyBox();
        };

        $scope.addNewAnswerToSurvey = function() {
            console.log($scope);
            console.log($scope.tempAnswer);

            if ($scope.tempAnswer == null || $scope.tempAnswer == '') {
                alert("An answer could not be empty!");
                return;
            }

            if ($scope.newSurvey == null) {
                $scope.newSurvey = {answers : []};
            }

            if ($scope.newSurvey.answers.indexOf($scope.tempAnswer) < 0)
                $scope.newSurvey.answers.push($scope.tempAnswer);
            else
                alert ("That is a duplicate answer!");
        };

        $scope.attemptVote = function(surveyId, answerId) {
            console.log(arguments);
            if (!$scope.user) {
                alert("Please login in order to submit your vote!");
                return;
            }
            var survey = $scope.surveys.filter(function(surv) {
                return surv.id === surveyId;
            });

            var selectedAnswer = survey.length && survey[0].answers.filter(function(answer){ return answer.id === answerId; });
            selectedAnswer = selectedAnswer.length && selectedAnswer[0];

            $http({
                method : 'POST',
                url : '/surveys/votes/save',
                params : {
                    answer_id : answerId,
                    user_id : $scope.user.id
                }
            }).success(function(data) {
                if (data.vote != null) {
                    alert("Congratulations! You have place your vote!");
                    $scope.getSurveyStatistic(data.vote.id);
                }
                else {
                    alert("Invalid input! Multiple voting?");
                }
            });
        };

        $scope.getSurveyStatistic = function(voteId) {
            $http({
                method : 'GET',
                url : '/surveys/stats/' + voteId
            })
            .success(function(data) {
                if (data.stats != null) {

                    console.log(data.stats);

                    var surveyId = data.stats.id;

                    var i = 0, found = -1;
                    for (i; i < $scope.surveys.length; i ++) {
                        if ($scope.surveys[i].id === surveyId) {
                            found = i;
                        }
                    }

                    console.log(found);
                    if (found > -1) {
                        $scope.surveys.splice(found, 1);
                        $scope.surveys.unshift(data.stats);
                        console.log($scope.surveys);
                    }
                }
            });
        };

        $scope.deleteSurvey = function(id) {
            $http({
                method : 'POST',
                url : "/surveys/" + id + "/delete"
            }).success(function(data) {

            });
        };

        $scope.disableSurvey = function(id) {
            $http({
                method : 'POST',
                url : "/surveys/" + id + "/disable"
            }).success(function(data) {
                alert("Survey successfully disabled!");
            });
        };

        $scope.enableSurvey = function(id) {
            $http({
                method : 'POST',
                url : "/surveys/" + id + "/enable"
            }).success(function(data) {
                alert("Survey successfully enabled!");
            });
        };

        $scope.takeSurveyAction = function(surveyId, action) {
            if (action === 'delete') {
                $scope.deleteSurvey(surveyId);
            }
            if (action === 'disable') {
                $scope.disableSurvey(surveyId);
            }
            if (action === 'enable') {
                $scope.enableSurvey(surveyId);
            }
            $scope.getActiveSurveys();
        }

    }]);
