'use strict';

const path = require('path'),
    gulp = require('gulp'),
    gutil = require('gulp-util'),
    express = require('express'),
    fallback = require('express-history-api-fallback'),
    webpack = require('webpack'),
    WebpackDevServer = require('webpack-dev-server'),
    devConfig = require('./webpack.dev.config.js'),
    prodConfig = require('./webpack.prod.config.js');

gulp.task("default", ["webpack-dev-server"]);

gulp.task("build-dev", ["webpack:build-dev"], () => {
    gulp.watch(["src/**/*"], ["webpack:build-dev"]);
});

// Production build
gulp.task("build", ["webpack:build"]);

gulp.task("webpack:build", cb => {
    webpack(Object.create(prodConfig), (err, stats) => {
        if (err) throw new gutil.PluginError("webpack:build", err);
        gutil.log("[webpack:build]", stats.toString({
            colors: true
        }));
        cb();
    });
});

gulp.task("webpack:build-dev", cb => {
    webpack(Object.create(devConfig)).run((err, stats) => {
        if (err) throw new gutil.PluginError("webpack:build-dev", err);
        gutil.log("[webpack:build-dev]", stats.toString({
            colors: true
        }));
    });
});

gulp.task("webpack-dev-server", cb => {
    // Start a webpack-dev-server
    new WebpackDevServer(webpack(Object.create(devConfig)), {
        publicPath: "/" + devConfig.output.publicPath,
        stats: {
            colors: true
        }
    }).listen(8000, "localhost", err => {
        if (err) throw new gutil.PluginError("webpack-dev-server", err);
        gutil.log("[webpack-dev-server]", "http://localhost:8000/webpack-dev-server/index.html");
    });
});