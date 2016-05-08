'use strict';

const path = require('path'),
    webpack = require('webpack'),
    baseConfig = require('./webpack.config.js'),
    prodConfig = Object.create(baseConfig);

prodConfig.entry = prodConfig.entry.concat(path.join(__dirname, 'src/main.js'));
prodConfig.plugins = prodConfig.plugins.concat(
    // http://vuejs.github.io/vue-loader/workflow/production.html
    new webpack.DefinePlugin({
        'process.env': {
            NODE_ENV: '"production"'
        }
    }),
    new webpack.optimize.DedupePlugin(),
    new webpack.optimize.UglifyJsPlugin({
        compress: {
            warnings: false
        }
    })
);

module.exports = prodConfig;