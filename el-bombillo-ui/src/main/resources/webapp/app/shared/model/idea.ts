module model {

    /** Enumeration of idea states. */
    export enum IdeaStatus {
        DEVELOPMENT,
        FINISHED
    }

    /** Representation of an idea. */
    export interface Idea {

        /** The path to the icon. */
        icon?:string;

        /** The title of the idea. */
        title?:string;

        /** The description of the idea. */
        description?:string;

        /** The activity rating in the interval of [1,3]. */
        activity?:number;

        /** The popularity rating in the interval of [1,3]. */
        popularity?:number;

        /** The status of the idea. */
        status?:IdeaStatus;

        /** All tags associated with this idea. */
        tags?:string[];

        /** All active members for the realization of the idea. */
        members?:User[];

    }
}
