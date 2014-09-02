var wordHunterApp = angular.module('wordHunterApp', []);

wordHunterApp.controller('FormCtrl', function($scope, $http, $log, $timeout) {
    $scope.query = '';

    var timeoutPromise;
    var delay = 500;

    $scope.$watch('query', function(query) {
        $timeout.cancel(timeoutPromise);
        timeoutPromise = $timeout(function() {
            $scope.loading = true;
            $http.get('/results?query='+query).then(function(data) {
                $log.debug(data.data.result);
                $scope.loading = true;
                if(data.data.result)
                    $scope.words = data.data.result;
            });
        }, delay);
    });

});