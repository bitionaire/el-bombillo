/// <reference path="../../../types/tsd.d.ts"/>
/// <reference path="../../../app.module.ts"/>
/// <reference path="../../../shared/service/ideaService.ts"/>
/// <reference path="../../../shared/model/idea.ts"/>

module controller.idea.overview {
    'use strict';

    /** The controller for the home view. */
    export class IdeaOverviewController {

        /** The idea service. */
        private ideaService: service.IdeaService;

        /**
         * Constructs a home controller.
         *
         * @param ideaService the idea service
         */
        constructor(ideaService: service.IdeaService) {
            this.ideaService = ideaService;

            this.ideas = ideaService.getAll();
        }

        /** List of ideas to display. */
        ideas: model.Idea[];
    }

    angular
        .module('app')
        .controller('IdeaOverviewController', IdeaOverviewController);
};