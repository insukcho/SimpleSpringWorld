// script for loading the page and checking for errors.
// run with ./node_modules/.bin/phantomjs integration_test/ghost.js

var page = require('webpage').create();
page.onConsoleMessage = function(message) {
    console.log(message);
};
page.onResourceRequested = function(request) {
    console.log('Request ' + JSON.stringify(request, undefined, 4));
};
page.onResourceReceived = function(response) {
    console.log('Response ' + JSON.stringify(response, undefined, 4));
};
page.onResourceError = function(error) {
    console.log('Error ' + JSON.stringify(error, undefined, 4));
};
page.onError = function(msg, trace) {
    console.log(msg);
    trace.forEach(function(item) {
        console.log('  ', item.file, ':', item.line);
    });
};
page.settings.userName = 'isi.cho@gmail.com';
page.settings.password = '1234';
page.open('http://localhost:3000', function(status) {
    console.log("Status: " + status);
    phantom.exit();
});
