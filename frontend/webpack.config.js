var webpack = require('webpack');
var vue = require('vue-loader');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {
        main: './src/main.js'
    },
    output: {
        path: './dist',
        filename: '[name].bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue'
            },
            {
                test: /\.js$/,
                exclude: /node_modules|vue\/src|vue-router\/|vue-loader\/|vue-hot-reload-api\//,
                loader: 'babel'
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract('style-loader', 'css-loader')
            },
            {
                test: /\.(jpeg|jpg|png|gif|svg)$/,
                loader: 'url?limit=8192'
            },
            {
                test: /\.scss$/,
                loader: 'style!css!sass'
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.vue']
    },
    plugins: [
        new ExtractTextPlugin('styles.css')
    ],
    babel: {
        "presets": [
            "es2015"
        ],
        "plugins": [
            "transform-runtime"
        ],
        cacheDirectory: true
    },
    // Vue settings
    vue: {
        loaders: {
            css: ExtractTextPlugin.extract("css"),
            sass: ExtractTextPlugin.extract('css!sass')
        }
    }
};

if (process.env.NODE_ENV === 'production') {
    // http://vuejs.github.io/vue-loader/workflow/production.html
    module.exports.plugins = (module.exports.plugins || []).concat([
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        }),
        new webpack.optimize.OccurenceOrderPlugin()
    ])
} else {
    module.exports.devtool = 'source-map'
}
