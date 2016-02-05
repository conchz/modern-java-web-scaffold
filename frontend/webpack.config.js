const path = require('path');

let VueLoader = require('vue-loader'), ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {
        main: './app/main.js'
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name].bundle.js',
        publicPath: './dist/'
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: VueLoader.withLoaders({
                    sass:  ExtractTextPlugin.extract('css!sass')
                })
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel',
                query: {
                    presets: ['es2015']
                }
            },
	    {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract('style-loader', 'css-loader')
            },
	    {
                test: /\.(jpeg|jpg|png|svg)$/,
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
    plugins: {
        new ExtractTextPlugin('styles.css')
    }
};
