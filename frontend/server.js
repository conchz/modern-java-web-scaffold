#!/usr/bin/env node

"use strict";

import path from 'path'
import express from 'express'
import favicon from 'serve-favicon'
import webpack from 'webpack'
import webpackMiddleware from 'webpack-dev-middleware'
import webpackHotMiddleware from 'webpack-hot-middleware'
import config from './webpack.config.js'

const isDev = process.env.NODE_ENV !== 'production';
const port = isDev ? 8000 : process.env.PORT;
const app = express();
const configFavicon = (app) => {
    app.use(favicon(path.join(__dirname, 'src/images/favicon.ico')))
};

if (isDev) {
    const compiler = webpack(config);
    const middleware = webpackMiddleware(compiler, {
        publicPath: config.output.publicPath,
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

    app.use(middleware);
    app.use(webpackHotMiddleware(compiler));
    configFavicon(app);
    app.get('*', (req, res) => {
        res.write(middleware.fileSystem.readFileSync(path.join(__dirname, 'dist/index.html')));
        res.end();
    });
} else {
    app.use(express.static(__dirname + '/dist'));
    configFavicon(app);
    app.get('*', (req, res) => {
        res.sendFile(path.join(__dirname, 'dist/index.html'));
    });
}

app.listen(port, '0.0.0.0', (err) => {
    if (err) {
        console.log(err);
    }
    console.info('==> 🌎 Listening on port %s. Open up http://0.0.0.0:%s/ in your browser.', port, port);
});
