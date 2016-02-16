"use strict";

let webpack = require('webpack');
let vue = require('vue-loader');
let ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = function (options) {
    let publicPath = options.devServer ? 'http://localhost:8001/assets/' : '/assets/';

    let output = {
        path: './dist',
        publicPath: publicPath,
        filename: '[name].bundle.js'
    };

    let plugins = [
        new ExtractTextPlugin('styles.css'),
        new webpack.NoErrorsPlugin()
    ];

    if (process.env.NODE_ENV === 'production') {
        // http://vuejs.github.io/vue-loader/workflow/production.html
        plugins = (plugins || []).concat([
            new webpack.DefinePlugin({
                "process.env": {
                    NODE_ENV: '"production"'
                }
            }),
            new webpack.optimize.UglifyJsPlugin({
                compress: {
                    warnings: false
                }
            }),
            new webpack.optimize.OccurenceOrderPlugin()
        ])
    } else {
        plugins = (plugins || []).concat([
            new webpack.DefinePlugin({
                "process.env": {
                    NODE_ENV: '"development"'
                }
            }),
            new webpack.HotModuleReplacementPlugin()
        ])
    }

    return {
        entry: {
            main: ['./src/main.js', 'webpack/hot/dev-server', 'webpack-dev-server/client?http://localhost:8001']
        },
        output: output,
        module: {
            loaders: [
                {
                    test: /\.vue$/,
                    loader: 'vue'
                },
                {
                    test: /\.js$/,
                    exclude: /node_modules|vue\/src|vue-router\/|vue-loader\/|vue-hot-reload-api\//,
                    loader: 'babel'
                },
                {
                    test: /\.css$/,
                    loader: ExtractTextPlugin.extract('style-loader', 'css-loader')
                },
                {
                    test: /\.(jpeg|jpg|png|gif|svg)$/,
                    loader: 'url?limit=8192'
                },
                {
                    test: /\.scss$/,
                    loader: 'style!css!sass'
                }
            ]
        },
        debug: options.debug,
        devtool: options.devtool,
        resolve: {
            extensions: ['', '.js', '.vue']
        },
        plugins: plugins,
        babel: {
            presets: ["es2015"],
            plugins: ["transform-runtime"],
            cacheDirectory: true
        },
        vue: {
            loaders: {
                css: ExtractTextPlugin.extract("css"),
                sass: ExtractTextPlugin.extract('css!sass')
            }
        }
    }
};