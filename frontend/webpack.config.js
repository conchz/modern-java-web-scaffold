"use strict";

let path = require('path');
let webpack = require('webpack');
let vue = require('vue-loader');
let ExtractTextPlugin = require('extract-text-webpack-plugin');
let HtmlWebpackPlugin = require('html-webpack-plugin');
let StatsWebpackPlugin = require('stats-webpack-plugin');

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
        extensions: ['', '.js', '.vue']
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.join(__dirname, 'index.html'),
            filename: 'index.html'
        }),
        new ExtractTextPlugin('styles.css'),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.NoErrorsPlugin()
    ],
    vue: {
        loaders: {
            css: ExtractTextPlugin.extract("css"),
            sass: ExtractTextPlugin.extract('css!sass')
        }
    }
};

if (process.env.NODE_ENV === 'production') {
    module.exports.entry = (module.exports.entry || []).concat([
        path.join(__dirname, 'src/main.js')
    ]);

    // http://vuejs.github.io/vue-loader/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
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
        new StatsWebpackPlugin('webpack.stats.json', {
            source: false,
            modules: false
        })
    ])
} else {
    module.exports.debug = true;
    module.exports.devtool = 'source-map';
    module.exports.entry = (module.exports.entry || []).concat([
        'webpack-hot-middleware/client?reload=true',
        path.join(__dirname, 'src/main.js')
    ]);
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: '"development"'
            }
        }),
        new webpack.HotModuleReplacementPlugin()
    ])
}
