/// <reference path="../../types/tsd.d.ts"/>
/// <reference path="../../app.module.ts"/>

module directive {
    'use strict';

    export class IdeaCardDirective implements ng.IDirective {
        restrict: string = 'A';
        templateUrl: string = 'idea-card-directive.html';
        scope = {

            // @property -> read only
            // =property -> two way binding

            id: '@id',
            idea: '=idea'
        };
    }

    angular
        .module("app")
        .directive('ideaCardDirective', () => new IdeaCardDirective());
};