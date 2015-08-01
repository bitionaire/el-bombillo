# Account service

The account service provides a RESTful interface to manage all user accounts.

We'll try to keep the documentation of this service as complete and up to date as possible, so it can be used as a template for other services. 

## Build & Run

This service is build with [Gradle](https://gradle.org/). The build process is defined by the `build.gradle` file within the root of this module.
It extends the build of the root project.

* `gradle build` will build the `.jar` package
* `gradle run` will run the service

### Dependencies

* `el-bombillo-infrastructure`: On execution of `gradle run` a Vagrant box will be started. This Box will provide a database for this service.

## Conventions

Some conventions that we should try to keep.

### Directory layout

| **Path** | **Description** |
| -------- | --------------- |
| `src/main/config/dev` | Configuration files for the development environment |
| `src/main/config/{enviornment}` | Configuration files for any other _environment_ |
| `src/main/java` | Service sources |
| `src/main/resources` | Service resources (e.g. schema definitions or locale specific resource bundles) |
| `src/test/java` | Test sources |
| `src/test/resources` | Test resources |

### Package layout

Package layout for this service, df. [Dropwizard - Organizing your project](http://www.dropwizard.io/manual/core.html#organizing-your-project):

* `org.bitionaire.elbombillo.account`
   * `api`: Constants and utilities for our API
   * `core`: Domain implementation and basic services used by this service
   * `model`: The domain object models
   * `persistence`: Data access objects and ORMs
   * `resources`: RESTful API resources
   * `tasks`: Administration tasks
   * `health`: Health checks