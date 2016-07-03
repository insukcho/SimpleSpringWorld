var baseConf = require('./wdio.conf');

exports.config = baseConf.configBuilder(baseConf.host, 3000, baseConf.username, baseConf.password);
