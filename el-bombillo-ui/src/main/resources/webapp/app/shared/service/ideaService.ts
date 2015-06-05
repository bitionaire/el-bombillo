/// <reference path="../../types/tsd.d.ts"/>
/// <reference path="../../app.module.ts"/>
/// <reference path="../model/idea.ts"/>

module service {
    'use strict';

    /** The idea service retrieves all ideas. */
    export class IdeaService {

        /**
         * Returns a list of all ideas.
         *
         * @returns {model.Idea[]} the list of ideas
         */
        getAll():model.Idea[] {
            var ideas : Array<model.Idea> = [];
            for (var i = 0; i < 9; i++) {
                ideas[i] = {
                    "title": "test " + i,
                    "description": "this is idea number " + i,
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
            return ideas;
        }

        get(id: number) : model.Idea {
            return  {
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
        .service('ideaService', IdeaService);
};