# modern-java-web-scaffold [![Build Status](https://travis-ci.org/lavenderx/modern-java-web-scaffold.svg?branch=master)](https://travis-ci.org/lavenderx/modern-java-web-scaffold)

## Introduction
**modern-java-web-scaffold** is a scaffold of front end and back end to build modern web application, using *[Gradle](http://gradle.org/)* as build tool, integrated frequently used framework, being applicable to RESTful architecture.

## Main Framework
#### Backend
* *[Spring Boot](http://docs.spring.io/spring-boot/docs/current/reference/html/)*
* *[Mybatis](http://mybatis.github.io/mybatis-3/)*
* *[Undertow](http://undertow.io/)*

#### Frontend
* *[Vue.js](http://vuejs.org/)*
* *[Webpack](https://webpack.github.io/)*
* *[Express](http://expressjs.com/)*
* *[Gulp](http://gulpjs.com/)*
* *[PM2](http://pm2.keymetrics.io/)*


## Quick Start
For backend application:
Using *Undertow* as embedded web server and *Docker* as runtime container for JVM applications. Executing command ``` ./bin/deploy-backend ``` to publish backend application. In order to debug, you can directly run WebAppBoot.java.

For frontend application:
Using *Vue.js* for the ViewModel, *Webpack* as module bundler, *Express* as frontend server, *PM2* as production process manager for Node.js applications.
```
npm install
npm start  // the same as `node server.js`
npm run build  // for production build

```