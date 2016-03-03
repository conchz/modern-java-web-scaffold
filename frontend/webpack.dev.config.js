"use strict";

const path = require('path'),
    webpack = require('webpack'),
    devConfig = require('./webpack.config.js');

devConfig.debug = true;
devConfig.devtool = 'source-map';
devConfig.entry = devConfig.entry.concat(
    'webpack-hot-middleware/client?reload=true',
    path.join(__dirname, 'src/main.js')
);
devConfig.plugins = devConfig.plugins.concat(new webpack.HotModuleReplacementPlugin());

module.exports = devConfig;