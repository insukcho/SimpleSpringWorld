const _ = require('lodash');
const path = require('path');
const DefinePlugin = require('webpack').DefinePlugin;
const ProvidePlugin = require('webpack').ProvidePlugin;
const CommonsChunkPlugin = require('webpack').optimize.CommonsChunkPlugin;
const HtmlWebpackPlugin = require('html-webpack-plugin');

var NODE_ENV = process.env.NODE_ENV || 'production';

module.exports = [{
    entry: {
        app: ['./src/entry.js'],
        vendor: _.without(_.keys(require('./package.json').dependencies), 'intl')
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name].entry.js',
        chunkFilename: '[id].js'
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: 'Tech Trial for Crossover',
            filename: 'entry.html'
        }),
        new ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery"
        }),
        new DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify(NODE_ENV)
            }
        }),
        new CommonsChunkPlugin('vendor', 'vendor.bundle.js')
    ],
    module: {
        loaders: [{
            test: /\.json$/,
            exclude: /node_modules/,
            loader: 'json'
        }]
    },
    resolve: {
        alias: {
            'underscore': 'lodash'
        }
    }
}]