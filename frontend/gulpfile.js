'use strict';

const path = require('path'),
    gulp = require('gulp'),
    gutil = require('gulp-util'),
    express = require('express'),
    favicon = require('serve-favicon'),
    rimraf = require('rimraf'),
    webpack = require('webpack'),
    webpackDevMiddleware = require('webpack-dev-middleware'),
    webpackHotMiddleware = require('webpack-hot-middleware'),
    devConfig = require('./webpack.dev.config.js'),
    prodConfig = require('./webpack.prod.config.js'),
    app = express(),
    port = (process.env.NODE_ENV !== 'production') ? 8000 : process.env.PORT,
    configFavicon = app => app.use(favicon(path.join(__dirname, 'src/assets/images/favicon.ico'))),
    startApp = (env, app) =>
        app.listen(port, 'localhost', err => {
            if (err) throw new gutil.PluginError(env + '-server', err);
            gutil.log('[' + env + '-server]', '==> Listening on port ' + port + '. Open up http://localhost:' + port + '/ in your browser.')
        });

gulp.task('default', ['dev-server']);

gulp.task('clean', () =>
    rimraf('dist/*', err => {
        if (err) throw new gutil.PluginError('clean', err)
    })
);

gulp.task('build-dev', ['clean', 'webpack:build-dev'], cb => {
    gulp.watch(['src/**/*'], ['webpack:build-dev']);
    cb()
});

// Production build
gulp.task('build', ['clean', 'webpack:build-prod']);

gulp.task('webpack:build-dev', cb =>
    webpack(Object.create(devConfig)).run((err, stats) => {
        if (err) throw new gutil.PluginError('webpack:build-dev', err);
        gutil.log('[webpack:build-dev]', stats.toString({
            colors: true
        }));

        cb()
    })
);

gulp.task('webpack:build-prod', cb =>
    webpack(Object.create(prodConfig), (err, stats) => {
        if (err) throw new gutil.PluginError('webpack:build-prod', err);
        gutil.log('[webpack:build-prod]', stats.toString({
            colors: true
        }));

        cb()
    })
);

gulp.task('dev-server', ['build-dev'], () => {
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
    configFavicon(app);
    app.get('*', (req, res) => {
        //res.write(devMiddleware.fileSystem.readFileSync(path.join(__dirname, 'dist/index.html')));
        //res.end();
        res.sendFile(path.join(__dirname, 'dist/index.html'));
    });

    startApp('dev', app)
});

gulp.task('prod-server', ['build'], () => {
    app.use(express.static(__dirname + '/dist'));
    configFavicon(app);
    app.get('*', (req, res) => {
        const match = req.url.match(/^\/assets\/.+\.(css|js|jpeg|jpg|png|gif)$/)[0];
        if (match != null) {
            res.sendFile(path.join(__dirname, 'dist', match.replace(/^\/assets\//, '')));
        } else {
            res.sendFile(path.join(__dirname, 'dist/index.html'));
        }
    });

    startApp('prod', app)
});