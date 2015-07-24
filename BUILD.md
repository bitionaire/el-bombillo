## Prerequisites

* Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Install [Gradle](https://gradle.org/)
* Install [Vagrant](https://www.vagrantup.com/downloads.html)
* Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)

## Build

* Run `gradle startVirtualBoxVm` in order to start background services within a Vagrant box
* Run `gradle run` (for whole project or a single module) to start your services
* Run `gradle stopVirtualBoxVm` in order to destroy your Vagrant box

That's it.

As most of our services is implemented via the [Dropwizard](http://www.dropwizard.io/) framework right now,
check-out the [Getting started](http://www.dropwizard.io/getting-started.html) guide on their homepage
and explore the configuration within the `build.gradle` files of each module.