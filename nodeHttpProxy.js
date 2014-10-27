var httpProxy = require('http-proxy');
httpProxy.createProxyServer({target: 'http://localhost:5050'}).listen(1338);