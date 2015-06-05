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
                templateUrl: "homeView.html",
                controller: "Home",
                controllerAs: "home"
            }).when("/idea/:id", {
                templateUrl: "idea.html",
                controller: "IdeaController",
                controllerAs: "ideaController"
            }).otherwise({
                redirectTo: "/"
            })
    };
})();
