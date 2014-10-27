
var http = require('http');
http.globalAgent.maxSockets = 500;


http.createServer(function (req, res) {
    console.log(req.url)
    var options = {
        //max: false,
        hostname: 'localhost',
        port: 5050,
        path: req.url,
        method: 'GET'
    };
    var outgoing = http.request(options, function(proxiedResponse) {
        proxiedResponse.setEncoding('utf8');
        var data ='' ;
        proxiedResponse.on('data', function (chunk) {
            data += chunk
        });
        proxiedResponse.on('end', function(){
            res.end(data);
        })
    });

    outgoing.on('error', function(e) {
        console.log('problem with request: ' + e.message);
    });

    outgoing.end();
}).listen(1337, '127.0.0.1');



