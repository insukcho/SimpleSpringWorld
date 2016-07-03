var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var HotModuleReplacementPlugin = require('webpack').HotModuleReplacementPlugin;
var config = require('./webpack.config');

//config.map(function (c) {
//	c.entry.app.unshift('webpack/hot/dev-server');
//	c.plugins.unshift(new HotModuleReplacementPlugin());
//	c.devtool = 'eval';
//});

new WebpackDevServer(webpack(config), {
    hot: true,
    historyApiFallback: true,
    proxy: {
        "/api/*": "http://localhost:8080/"
    }
}).listen(3000, 'localhost', function (err, result) {
    if (err) {
        console.log(err);
    }

    console.log('Listening at localhost:3000');
});
