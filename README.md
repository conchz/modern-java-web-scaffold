# modern-java-web-scaffold [![Build Status](https://travis-ci.org/dolphineor/modern-java-web-scaffold.svg?branch=master)](https://travis-ci.org/dolphineor/modern-java-web-scaffold)

## Introduction
**modern-java-web-scaffold** is a scaffold of front end and back end to build modern web application, using *[Gradle](http://gradle.org/)* as build tool, integrated frequently used framework, being applicable to RESTFul architecture.

## Main Framework
#### Backend
* *[Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)*
* *[Mybatis](http://mybatis.github.io/mybatis-3/)*
* *[Undertow](http://undertow.io/)*
* *[Capsule](http://www.capsule.io/)*

#### Frontend
* *[Vue.js](http://vuejs.org/)*
* *[Webpack](https://webpack.github.io/)*
* *[Express](http://expressjs.com/)*
* *[PM2](http://pm2.keymetrics.io/)*


## Quick Start
For backend application:
Using *Undertow* as embedded web server and *Capsule* as Packaging and Deployment for JVM Applications. Executing command ``` gradle :backend:clean fatCapsule ``` to package a executable jar. In order to debug, you can directly run WebAppBoot.java.

For frontend application:
Using *Vue.js* for the ViewModel, *Webpack* as module bundler, *Express* as frontend server, *PM2* as production process manager for Node.js applications.
```
gradle :frontend:npm_install    // the same as `npm install`
gradle :frontend:npmRunWithDev  // the same as `npm start`, or `node server.js`

```