#!/usr/bin/env node
"use strict";
var http = require('http'),
    finalhandler = require('finalhandler'),
    argv = require('minimist')(process.argv.slice(2)),
    serveIndex = require('serve-index'),
    serveStatic = require('serve-static');

const rootDir = argv._[0] || process.cwd();
const PORT = 8000;

let index = serveIndex(rootDir, {
    icons: true,
    hidden: true
});

let serve = serveStatic(rootDir, {
    index: 'index.html'
});

let server = http.createServer((request, response) => {
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