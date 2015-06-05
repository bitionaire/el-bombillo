/// <reference path="types/tsd.d.ts"/>
/// <reference path="app.module.ts"/>

(function() {
    'use strict';

    angular
        .module('app')
        .config(config);

    function config($routeProvider : ng.route.IRouteProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "idea-overview.html",
                controller: "IdeaOverviewController",
                controllerAs: "home"
            }).when("/idea/:id", {
                templateUrl: "idea-detail.html",
                controller: "IdeaDetailController",
                controllerAs: "ideaController"
            }).otherwise({
                redirectTo: "/"
            })
    };
})();
