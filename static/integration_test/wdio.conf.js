const path = require('path');
const fs = require('fs');
const _ = require('lodash');

exports.host = 'localhost';
exports.port = 8080;
exports.username = 'isi.cho@gmail.com';
exports.password = '1234';

function configBuilder(host, port, username, password) {
    var config = {
        //
        // ==================
        // Specify Test Files
        // ==================
        // Define which test specs should run. The pattern is relative to the directory
        // from which `wdio` was called. Notice that, if you are calling `wdio` from an
        // NPM script (see https://docs.npmjs.com/cli/run-script) then the current working
        // directory is where your package.json resides, so `wdio` will be called from there.
        //
        specs: [
            './integration_test/specs/**/*.js'
        ],
        // Patterns to exclude.
        exclude: [
            // 'path/to/excluded/files'
        ],
        //
        // ============
        // Capabilities
        // ============
        // Define your capabilities here. WebdriverIO can run multiple capabilities at the same
        // time. Depending on the number of capabilities, WebdriverIO launches several test
        // sessions. Within your capabilities you can overwrite the spec and exclude options in
        // order to group specific specs to a specific capability.
        //
        // First, you can define how many instances should be started at the same time. Let's
        // say you have 3 different capabilities (Chrome, Firefox, and Safari) and you have
        // set maxInstances to 1; wdio will spawn 3 processes. Therefore, if you have 10 spec
        // files and you set maxInstances to 10, all spec files will get tested at the same time
        // and 30 processes will get spawned. The property handles how many capabilities
        // from the same test should run tests.
        //
        capabilities: [{
            browserName: 'phantomjs',
            'phantomjs.page.customHeaders.Authorization': `Basic ${new Buffer(username + ':' + password).toString('base64')}`
        }],
        //
        // ===================
        // Test Configurations
        // ===================
        // Define all options that are relevant for the WebdriverIO instance here
        //
        // By default WebdriverIO commands are executed in a synchronous way using
        // the wdio-sync package. If you still want to run your tests in an async way
        // e.g. using promises you can set the sync option to false.
        // sync: true,
        //
        // Level of logging verbosity: silent | verbose | command | data | result | error
        logLevel: 'silent',
        //
        // Enables colors for log output.
        coloredLogs: true,
        //
        // Saves a screenshot to a given path if a command fails.
        screenshotPath: './errorShots/',
        //
        // Default timeout for all waitFor* commands.
        waitforTimeout: 50000,
        //
        // Default timeout in milliseconds for request
        // if Selenium Grid doesn't send response
        connectionRetryTimeout: 90000,
        //
        // Default request retries count
        connectionRetryCount: 3,
        //
        // Initialize the browser instance with a WebdriverIO plugin. The object should have the
        // plugin name as key and the desired plugin options as properties. Make sure you have
        // the plugin installed before running any tests. The following plugins are currently
        // available:
        // WebdriverCSS: https://github.com/webdriverio/webdrivercss
        // WebdriverRTC: https://github.com/webdriverio/webdriverrtc
        // Browserevent: https://github.com/webdriverio/browserevent
        // plugins: {
        //     webdrivercss: {
        //         screenshotRoot: 'my-shots',
        //         failedComparisonsRoot: 'diffs',
        //         misMatchTolerance: 0.05,
        //         screenWidth: [320,480,640,1024]
        //     },
        //     webdriverrtc: {},
        //     browserevent: {}
        // },
        //
        // Test runner services
        // Services take over a specific job you don't want to take care of. They enhance
        // your test setup with almost no effort. Unlike plugins, they don't add new
        // commands. Instead, they hook themselves up into the test process.
        // services: [],//
        // Framework you want to run your specs with.
        // The following are supported: Mocha, Jasmine, and Cucumber
        // see also: http://webdriver.io/guide/testrunner/frameworks.html
        //
        // Make sure you have the wdio adapter package for the specific framework installed
        // before running any tests.
        framework: 'jasmine',
        //
        // Test reporter for stdout.
        // The following are supported: dot (default), spec, and xunit
        // see also: http://webdriver.io/guide/testrunner/reporters.html
        reporters: ['dot'],
        //
        // Options to be passed to Jasmine.
        jasmineNodeOpts: {
            //
            // Jasmine default timeout
            defaultTimeoutInterval: 60000,
            //
            // The Jasmine framework allows interception of each assertion in order to log the state of the application
            // or website depending on the result. For example, it is pretty handy to take a screenshot every time
            // an assertion fails.
            expectationResultHandler: function (passed, assertion) {
                if (passed) {
                    return;
                }
                var shotsDir = path.join(__dirname, 'report');
                try {
                    fs.accessSync(shotsDir, fs.F_OK);
                } catch (e) {
                    fs.mkdirSync(shotsDir);
                }
                var title = assertion.message.replace(/\s/g, '-');
                browser.saveScreenshot(path.join(shotsDir, `assertionError_${title}.png`));
            }
        }
    };

    // Set a base URL in order to shorten url command calls. If your url parameter starts
    // with "/", then the base url gets prepended.
    if (!_.some(config.capabilities, {browserName: 'phantomjs'})) {
        config.baseUrl = `http://${encodeURIComponent(username)}:${password}@${host}:${port}`
    } else {
        config.baseUrl = `http://${host}:${port}`;
    }

    return config;
}

exports.configBuilder = configBuilder;
exports.config = configBuilder(exports.host, exports.port, exports.username, exports.password);
