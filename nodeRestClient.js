
var http = require('http'), RestClient = require('node-rest-client').Client;

var rest = new RestClient();
http.globalAgent.maxSockets = 500;


http.createServer(function (req, res) {
    rest.get('http://localhost:5050' + req.url, function(data){

        res.end(data);
    })
}).listen(1339, '127.0.0.1');



