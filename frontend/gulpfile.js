'use strict';

const path = require('path'),
    gulp = require('gulp'),
    gutil = require('gulp-util'),
    express = require('express'),
    fallback = require('express-history-api-fallback'),
    webpack = require('webpack'),
    WebpackDevServer = require('webpack-dev-server'),
    webpackConfig = require('./webpack.config.js');

gulp.task("default", ["webpack-dev-server"]);

gulp.task("build-dev", ["webpack:build-dev"], () => {
    gulp.watch(["src/**/*"], ["webpack:build-dev"]);
});

// Production build
gulp.task("build", ["webpack:build"]);

gulp.task("webpack:build", (callback) => {
    // modify some webpack config options
    const prodConfig = Object.create(webpackConfig);
    prodConfig.entry = prodConfig.entry.concat(path.join(__dirname, "src/main.js"));
    // http://vuejs.github.io/vue-loader/workflow/production.html
    prodConfig.plugins = prodConfig.plugins.concat(
        new webpack.DefinePlugin({
            "process.env": {
                "NODE_ENV": '"production"'
            }
        }),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin()
    );

    // run webpack
    webpack(prodConfig, (err, stats) => {
        if (err) throw new gutil.PluginError("webpack:build", err);
        gutil.log("[webpack:build]", stats.toString({
            colors: true
        }));
        callback();
    });
});

const devConfig = Object.create(webpackConfig);
devConfig.devtool = "source-map";
devConfig.debug = true;
devConfig.entry = devConfig.entry.concat(
    "webpack-hot-middleware/client?reload=true",
    path.join(__dirname, "src/main.js")
);
devConfig.plugins = devConfig.plugins.concat(
    new webpack.DefinePlugin({
        "process.env": {
            NODE_ENV: '"development"'
        }
    }),
    new webpack.HotModuleReplacementPlugin()
);

const devCompiler = webpack(devConfig);

gulp.task("webpack:build-dev", function (callback) {
    // run webpack
    devCompiler.run((err, stats) => {
        if (err) throw new gutil.PluginError("webpack:build-dev", err);
        gutil.log("[webpack:build-dev]", stats.toString({
            colors: true
        }));
        callback();
    });
});

gulp.task("webpack-dev-server", (callback) => {
    // modify some webpack config options
    const devConfig = Object.create(webpackConfig);
    devConfig.devtool = "source-map";
    devConfig.debug = true;

    // Start a webpack-dev-server
    new WebpackDevServer(webpack(devConfig), {
        publicPath: "/" + devConfig.output.publicPath,
        stats: {
            colors: true
        }
    }).listen(8000, "localhost", (err) => {
        if (err) throw new gutil.PluginError("webpack-dev-server", err);
        gutil.log("[webpack-dev-server]", "http://localhost:8000/webpack-dev-server/index.html");
    });
});