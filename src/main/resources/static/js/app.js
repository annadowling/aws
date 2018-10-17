angular.module('SpringAWS', ['aws', 'errors', 'status', 'info', 'ngRoute', 'ui.directives']).
    config(function ($locationProvider, $routeProvider) {
        // $locationProvider.html5Mode(true);

        $routeProvider.when('/errors', {
            controller: 'ErrorsController',
            templateUrl: 'templates/errors.html'
        });
        $routeProvider.otherwise({
            controller: 'AwsController',
            templateUrl: 'templates/aws.html'
        });

    console.log("Got into app.js");
    }
);
