const _ = require('lodash');

describe('the app', function () {
    beforeEach(function *() {
        yield browser
            .setViewportSize({width: 1366, height: 768}, true)
            .timeoutsImplicitWait(1000)
            .url('/index.html');
    });

    it('greets the user', function *() {
        expect(yield browser.getText('.cocntainer')).toContain('1.0.0');

    });

});

function elementHasText(selector, text) {
    return function () {
        return this.getText(selector).then(function (t) {
        	return _.includes(t, text);
        })
    };
}