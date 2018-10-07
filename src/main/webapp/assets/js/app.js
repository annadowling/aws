angular.module('SpringAWS', ['aws', 'errors', 'status', 'info', 'ngRoute', 'ui.directives']).
    config(function ($locationProvider, $routeProvider) {
        // $locationProvider.html5Mode(true);

        $routeProvider.when('/errors', {
            controller: 'ErrorsController',
            templateUrl: 'assets/templates/errors.html'
        });
        $routeProvider.otherwise({
            controller: 'AwsController',
            templateUrl: 'assets/templates/aws.html'
        });

    console.log("Got into app.js");
    }
);
