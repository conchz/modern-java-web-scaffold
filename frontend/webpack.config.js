module.exports = {
    entry: './app/main',
    output: {
        filename: 'bundle.js'
    },
    module: {
        loaders: [{
            test: /\.js$/,
            exclude: /node_modules/,
            loader: 'babel',
            query: {
                presets: ['es2015']
            }
        }, {
            test: /\.html$/,
            loader: 'raw'
        }]
    }
};
