var _ = require('lodash');
var Rx = require('rx');
var RootView = require('../view/root');

module.exports = function (options) {

	var initialState = require('../initial_state');

    var eventSubject = new Rx.BehaviorSubject(_.identity);

    var appStore = eventSubject.asObservable().scan(function (acc, currentValue) {
        return currentValue(acc);
    }, initialState).shareReplay(1);
    
    debugger;

    var rootView = new RootView ({
        appStore: appStore
    });
	
	options.$container.append(rootView.$el);
}