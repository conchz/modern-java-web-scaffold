#!/usr/bin/env node

"use strict";

import path from 'path'
import express from 'express'
import fallback from 'express-history-api-fallback'
import favicon from 'serve-favicon'
import webpack from 'webpack'
import webpackDevMiddleware from 'webpack-dev-middleware'
import webpackHotMiddleware from 'webpack-hot-middleware'

const isDev = process.env.NODE_ENV !== 'production';
const port = isDev ? 8000 : process.env.PORT;
const app = express();
const configFavicon = (app) => {
    app.use(favicon(path.join(__dirname, 'src/images/favicon.ico')))
};

if (isDev) {
    let devConfig = require('./webpack.dev.config.js');

    const compiler = webpack(devConfig);
    const devMiddleware = webpackDevMiddleware(compiler, {
        publicPath: devConfig.output.publicPath,
        contentBase: 'src',
        stats: {
            colors: true,
            hash: false,
            timings: true,
            chunks: false,
            chunkModules: false,
            modules: false
        }
    });

    app.use(devMiddleware);
    app.use(webpackHotMiddleware(compiler));
    app.use(fallback('dist/index.html', {root: __dirname}));
    configFavicon(app);
    app.get('*', (req, res) => {
        res.write(middleware.fileSystem.readFileSync(path.join(__dirname, 'dist/index.html')));
        res.end();
    });
} else {
    app.use(express.static(__dirname + '/dist'));
    app.use(fallback('dist/index.html', {root: __dirname}));
    configFavicon(app);
    app.get('*', (req, res) => {
        res.sendFile(path.join(__dirname, 'dist/index.html'));
    });
}

app.listen(port, '0.0.0.0', (err) => {
    if (err) {
        console.log(err);
    }
    console.info('==> Listening on port %s. Open up http://0.0.0.0:%s/ in your browser.', port, port);
});
