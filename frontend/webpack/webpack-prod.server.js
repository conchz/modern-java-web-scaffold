"use strict";

let webpack = require('webpack');
let WebpackDevServer = require('webpack-dev-server');
let proxy = require('proxy-middleware');
let url = require('url');
let config = require('./../make-webpack-config')({
    devServer: false,
    debug: false
});

const PORT = 8001;

module.exports = function (app) {
    app.use('/assets', proxy(url.parse('http://localhost:' + PORT + '/assets')));

    let server = new WebpackDevServer(webpack(config), {
        contentBase: __dirname,
        hot: false,
        quiet: false,
        noInfo: true,
        publicPath: '/assets/',
        stats: {colors: true}
    });

    server.listen(PORT, 'localhost', () => {
        console.log('Server listening on: http://localhost:%s', PORT)
    })
};