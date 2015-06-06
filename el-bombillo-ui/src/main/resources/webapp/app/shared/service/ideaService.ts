/// <reference path="../../types/tsd.d.ts"/>
/// <reference path="../../app.module.ts"/>
/// <reference path="../model/idea.ts"/>

module service {
    'use strict';

    interface IdeaResource extends ng.resource.IResourceClass<model.Idea> {
        all() : model.Idea[];
    }

    angular.module("app").factory('IdeaResource', ['$resource', ($resource:ng.resource.IResourceService):IdeaResource => {
        // Define your custom actions here as IActionDescriptor
        var getAllAction:ng.resource.IActionDescriptor = {
            method: 'GET',
            isArray: true
        };

        // Return the resource, include your custom actions
        return <IdeaResource> $resource('/example/idea/ideas.json', {}, {
            all: getAllAction
        });
    }]);

    /** The idea service retrieves all ideas. */
    export class IdeaService {

        private resource:IdeaResource;

        constructor($resource:IdeaResource) {
            this.resource = $resource;
        }

        /**
         * Returns a list of all ideas.
         *
         * @returns {model.Idea[]} the list of ideas
         */
        all():model.Idea[] {
            return this.resource.all();
        }

        find(id:number):model.Idea {
            return {
                "title": "test",
                "description": "this is idea",
                "popularity": 1,
                "activity": 1,
                "status": model.IdeaStatus.FINISHED,
                "tags": [
                    "Idea",
                    "IT"
                ],
                "members": [
                    {
                        "name": "bitionaire"
                    },
                    {
                        "name": "netdevfighter"
                    }
                ]
            };
        }
    }

    angular
        .module('app')
        .service('ideaService', ["IdeaResource", IdeaService]);
}
;