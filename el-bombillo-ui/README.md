# Cauting: Inactive

This is just a preliminary prototype that might serve as a copy & paste source.

## Prerequisites

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Gradle](https://gradle.org/) > 2.2

## Running the WebApp

* Execute `gradle run` and start editing your web resources in the directory `src/main/resources/webapp`
* A [webserver](http://localhost:8080/index.html) will be started on port `8080`, where you'll see the `index.html`
* A _Grunt_ watch job will be started, so your `run` command won't quit and instead watch your resources and update your build on change (save)

## Adding build targets

* Edit the `Gruntfile.js` ([more about Grunt](http://gruntjs.com/))
* `gradle` will run the default task and `watch` afterwards on execution of the `run` task
