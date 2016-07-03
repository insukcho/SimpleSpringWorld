const _ = require('lodash');
const path = require('path');
const webpack = require('webpack');
const config = require('./webpack.config');

module.exports = _.map(_.cloneDeep(config), function (c) {
    c.plugins.push(new webpack.optimize.DedupePlugin());
    c.plugins.push(new webpack.optimize.OccurrenceOrderPlugin());
    c.plugins.push(new webpack.optimize.UglifyJsPlugin({
        exclude: ['vendor.bundle.js']
    }));
    c.module.postLoaders = [{
        test: /\.js$/,
        include: [path.resolve(__dirname, "node_modules")],
        exclude: /node_modules\/(jquery|mori)\//,
        loader: "uglify"
    }];
    c['uglify-loader'] = {
        mangle: true,
        compress: true
    };
    return c;
});
