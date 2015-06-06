/// <reference path="../../../types/tsd.d.ts"/>
/// <reference path="../../../app.module.ts"/>
/// <reference path="../../../shared/service/ideaService.ts"/>
/// <reference path="../../../shared/model/idea.ts"/>

module controller.idea.detail {
    'use strict';

    interface IdeaDetailRouteParams extends ng.route.IRouteParamsService {
        id?: number;
    }

    /** The controller for the home view. */
    export class IdeaDetailController {

        /** The idea service. */
        private ideaService: service.IdeaService;

        /**
         * Constructs a home controller.
         *
         * @param ideaService the idea service
         */
        constructor($routeParams: IdeaDetailRouteParams, ideaService: service.IdeaService) {
            this.ideaService = ideaService;

            var _self = this;
            ideaService.find($routeParams.id).then(function(response) {
                _self.idea = response;
            });
        }

        idea: model.Idea;
    }

    angular
        .module('app')
        .controller('IdeaDetailController', IdeaDetailController);
};