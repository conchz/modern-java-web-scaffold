"use strict";

let webpack = require('webpack');
let WebpackDevServer = require('webpack-dev-server');
let proxy = require('proxy-middleware');
let url = require('url');
let config = require('./../make-webpack-config')({
    devServer: true,
    devtool: 'source-map',
    debug: true
});

const PORT = 8001;

module.exports = function (app) {
    app.use('/assets', proxy(url.parse('http://localhost:' + PORT + '/assets')));

    let server = new WebpackDevServer(webpack(config), {
        contentBase: __dirname,
        hot: true,
        quiet: false,
        noInfo: false,
        publicPath: '/assets/',
        stats: {
            cached: false,
            colors: true
        }
    });

    server.listen(PORT, 'localhost', () => {
        console.log('WebpackDevServer listening on: http://localhost:%s', PORT)
    })
};