var Backbone = require('backbone');
var h = require('hyperscript');

module.exports = Backbone.View.extend({
	className: 'root-view',
	
	initialize: function (options) {
		debugger;
		this.render(options.appStore);
	},
	
	render: function (data) {
		debugger;
		this.$el.append(
			h('.container', data.version)
		);
	}
});