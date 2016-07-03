var Rx = require('rx');

var RootView = require('../../src/view/root');

describe('RootView', function () {
	var subject;
	
	beforeEach(function () {
		subject = new RootView({
			appStore: {
				version: '1.2.3'
			}
		});
	});
	
	it('Rendering correctly', function () {
		expect(subject.$el.find('.container').text()).toEqual('1.2.3');
	});
});