#!/usr/bin/env node

"use strict";

let express = require('express');
let app = express();

const PORT = 8000;

//if (process.env.NODE_ENV === 'production') {
//    require('./webpack/webpack-prod.server.js')(app)
//} else {
//    require('./webpack/webpack-dev.server.js')(app)
//}

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/index.html')
});

app.listen(PORT, () => {
    console.log('Server listening on: http://localhost:%s', PORT)
});
