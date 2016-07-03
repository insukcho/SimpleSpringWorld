const gulp = require('gulp');
const jasmineBrowser = require('gulp-jasmine-browser');
const ProvidePlugin = require('webpack').ProvidePlugin;
const DefinePlugin = require('webpack').DefinePlugin;
const webpack = require('webpack-stream');
const plumber = require('gulp-plumber');

const config = require('../webpack.config')[0];

var providePlugin = new ProvidePlugin({
    $: "jquery",
    jQuery: "jquery",
    "window.jQuery": "jquery"
});

var definePlugin = new DefinePlugin({
    "process.env": {
        NODE_ENV: JSON.stringify('production')
    }
});

var specPaths = [
    'spec/**/*_spec.js'
];

gulp.task('jasmine', function () {
    var JasminePlugin = require('gulp-jasmine-browser/webpack/jasmine-plugin');
    var jasminePlugin = new JasminePlugin();
    return gulp.src(specPaths)
        .pipe(plumber())
        .pipe(webpack({
            devtool: 'eval',
            watch: true,
            output: {filename: 'spec.js'},
            plugins: [
                jasminePlugin,
                providePlugin,
                definePlugin
            ],
            module: config.module,
            resolve: config.resolve
        }))
        .pipe(jasmineBrowser.specRunner())
        .pipe(jasmineBrowser.server({whenReady: jasminePlugin.whenReady}));
});

gulp.task('jasmine-phantom', function () {
    return gulp.src(specPaths)
        .pipe(webpack({
            output: {filename: 'spec.js'},
            plugins: [
                providePlugin,
                definePlugin
            ],
            module: config.module,
            resolve: config.resolve
        }))
        .pipe(jasmineBrowser.specRunner({console: true}))
        .pipe(jasmineBrowser.headless());
});