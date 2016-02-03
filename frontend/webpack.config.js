module.exports = {
    entry: './app/main.js',
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: './dist/'
    },
    module: {
        loaders: [
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
                loader: 'style!css'
            },
	    {
                test: /\.(jpg|png)$/,
                loader: 'url?limit=8192'
            }
        ]
    }
};
