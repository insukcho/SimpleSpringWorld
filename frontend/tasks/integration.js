const gulp = require('gulp');
const path = require('path');
const http = require('http');
const exec = require('child_process').exec;
const spawn = require('child_process').spawn;
const selenium = require('selenium-standalone');
const webdriver = require('gulp-webdriver');
const clean = require('gulp-clean');
const gutil = require('gulp-util');
const phantomjs = require('phantomjs-prebuilt');

const isWindows = /^win/.test(process.platform);

var springProcess, seleniumProcess;

gulp.task('selenium-install', function (done) {
    selenium.install(done);
});

gulp.task('selenium-start', ['selenium-install'], function (done) {
	var opts = {};
	if (isWindows) {
		opts.seleniumArgs = ["--", `-Dphantomjs.binary.path=${phantomjs.path}`];
	}
	
    selenium.start(opts, function (err, child) {
//    	child.stderr.on('data', function(data){
//    	    console.log(data.toString());
//    	});
    	
        seleniumProcess = child;
        done();
    });
});

gulp.task('spring-build', function (done) {
    var finished = false;
    console.log(path);
    exec(`${isWindows ? 'gradlew' : './gradlew'} assemble`, {cwd: path.join(process.cwd(), '..')}, function (error) {
        if (error) {
        	console.log(error);
            throw error;
        }
        finished = true;
    });
    function poll() {
        gutil.log(gutil.colors.yellow('assembling...'));
        setTimeout(function () {
            if (finished) {
                done();
            } else {
                poll();
            }
        }, 10 * 1000);
    }
    poll();
});

gulp.task('spring-start', ['spring-build'], function () {
    var jar = path.join('build', 'libs', 'tech-trial-0.0.1.jar');
    springProcess = spawn('java', ['-jar', jar], {cwd: path.join(process.cwd(), '..')});
});

gulp.task('spring-ready', ['spring-start'], (function checkServer(tries) {
    return function (done) {
        http.request({port: 8080}, function (response) {
            console.log(response.statusCode + ' ' + response.statusMessage);
            done();
        }).on('error', function () {
            if (tries < 1) {
                console.log("Error: can't connect to spring server, giving up.");
                process.exit(1);
            } else {
                console.log('waiting for spring server...');
                setTimeout(function () {
                    checkServer(tries - 1)(done);
                }, 3000);
            }
        }).end();
    }
})(10));

gulp.task('clean-integration-report', function () {
    return gulp.src('integration_test/report', {read: false})
        .pipe(clean());
});

gulp.task('integration-test', ['selenium-start', 'spring-ready', 'clean-integration-report'], function () {
    var tearDown = function () {
        if (springProcess) springProcess.kill();
        if (seleniumProcess) seleniumProcess.kill();
    };

    return gulp.src('integration_test/wdio.conf.js')
        .pipe(webdriver())
        .on('end', tearDown)
        .on('error', tearDown);
});

gulp.task('integration-fast', ['selenium-start', 'clean-integration-report'], function () {
    var tearDown = function () {
        if (seleniumProcess) seleniumProcess.kill();
    };

    return gulp.src('integration_test/wdio.fast.conf.js')
        .pipe(webdriver())
        .on('end', tearDown)
        .on('error', tearDown);
});
