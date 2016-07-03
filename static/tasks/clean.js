const gulp = require('gulp');
const clean = require('gulp-clean');

gulp.task('clean', ['clean-integration-report'], function () {
    return gulp.src(['dist'], {read: false})
        .pipe(clean());
});