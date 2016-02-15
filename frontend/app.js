#!/usr/bin/env node

'use strict';

var http = require('http'),
    finalhandler = require('finalhandler'),
    argv = require('minimist')(process.argv.slice(2)),
    serveIndex = require('serve-index'),
    serveStatic = require('serve-static');

const rootDir = argv._[0] || process.cwd();
const PORT = 8000;

var index = serveIndex(rootDir, {
    icons: true,
    hidden: true
});

var serve = serveStatic(rootDir, {
    index: './index.html'
});

var server = http.createServer((request, response) => {
    let done = finalhandler(request, response);
    serve(request, response, (err) => {
        if (err) {
            return done(err);
        }
        index(request, response, done);
    });
});

server.listen(PORT, () => {
    console.log('Server listening on: http://localhost:%s', PORT);
});