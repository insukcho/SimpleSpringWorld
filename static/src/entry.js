module.exports = function () {
    var application = require('./controller/application');

    application({
        $container: $('body')
    });
}