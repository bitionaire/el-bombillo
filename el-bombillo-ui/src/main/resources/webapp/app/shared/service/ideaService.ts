/// <reference path="../../types/tsd.d.ts"/>
/// <reference path="../../app.module.ts"/>
/// <reference path="../model/idea.ts"/>

module service {
    'use strict';

    interface Idea extends ng.resource.IResource<model.Idea> {}

    interface IdeaResource extends ng.resource.IResourceClass<Idea> {
        all() : Idea[];
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
        private q: ng.IQService;

        constructor($resource:IdeaResource, $q: ng.IQService) {
            this.resource = $resource;
            this.q = $q;
        }

        /**
         * Returns a list of all ideas.
         *
         * @returns {model.Idea[]} the list of ideas
         */
        all():model.Idea[] {
            return this.resource.all();
        }

        find(id:number): ng.IPromise<model.Idea> {
            var deferred = this.q.defer();

            this.all().$promise.then(function(ideas) {
                console.log(ideas);
                deferred.resolve(ideas[id]);
            })

            return deferred.promise;
        }
    }

    angular
        .module('app')
        .service('ideaService', ["IdeaResource", "$q", IdeaService]);
}
;