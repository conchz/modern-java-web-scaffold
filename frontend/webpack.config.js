"use strict";

const path = require('path');
const webpack = require('webpack');
const vue = require('vue-loader');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: [],
    output: {
        path: path.join(__dirname, 'dist'),
        publicPath: '/assets/',
        filename: '[name].bundle.js'
    },
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
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'file'
            },
            {
                test: /\.(woff|woff2)$/,
                loader: 'url?prefix=font/&limit=10000'
            },
            {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?limit=10000&mimetype=application/octet-stream'
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: 'url?limit=10000&mimetype=image/svg+xml'
            },
            {
                test: /\.scss$/,
                loader: 'style!css!sass'
            },
            {
                test: /\.html$/,
                loader: 'html'
            }
        ]
    },
    resolve: {
        root: path.join(__dirname, 'node_modules'),
        extensions: ['', '.js', '.vue']
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.join(__dirname, 'src/index.html'),
            filename: 'index.html',
            inject: true
        }),
        new ExtractTextPlugin('styles.css'),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.NoErrorsPlugin()
    ],
    vue: {
        loaders: {
            js: 'babel',
            css: ExtractTextPlugin.extract("css"),
            sass: ExtractTextPlugin.extract('css!sass')
        }
    }
};
