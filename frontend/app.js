#!/usr/bin/env node

"use strict";

let http = require('http'),
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

let serve = serveStatic(rootDir);

let server = http.createServer((req, res) => {
    let done = finalhandler(req, res);
    serve(req, res, (err) => {
        if (err) {
            return done(err)
        }

        index(req, res, done)
    })
});

server.listen(PORT, () => {
    console.log('Server listening on: http://localhost:%s', PORT);
});
