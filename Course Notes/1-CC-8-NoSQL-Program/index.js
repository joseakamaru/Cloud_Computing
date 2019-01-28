require('dotenv').config();
var express = require("express");
var app = express();
var bodyParser = require('body-parser')
app.use(bodyParser.urlencoded({ extended: false }))

var account = process.env.account;
var password = process.env.password;
var host = process.env.host;
var url = 'https://' + account + ':' + password + '@' + host;
var cloudant = require('cloudant-quickstart')
var db = cloudant(url, 'sales');

db.create().then(function(result) {
			console.log(result);
		}, function(err){
			console.log(err);
    	});
/*
db.info().then(console.log,
	function(err) {
	    console.log(err);
    });
*/
app.get('/insert.html', function (req, res) {
   res.sendFile( __dirname + "/" + "insert.html" );
})
app.get('/insert', function (req, res) {
	var person = {
	  name: req.query.name,
	  email: req.query.email,
	  address: {
	  	Street: req.query.street,
	  	city: req.query.city,
	  	state: req.query.state
  	  }
	};
	db.insert(person);
   	res.end(JSON.stringify(person));
});

app.get('/getByName.html', function (req, res) {
   res.sendFile( __dirname + "/" + "getByName.html" );
})
app.get('/getByName', function (req, res) {
	db.query({name: {'$eq': req.query.name}})
	    .then(function(result) {
			res.write(result[0].name+'\n');
			res.write(result[0]._id+'\n');
			res.write(result[0].email+'\n');
			res.write(result[0].address.street+'\n');
			res.write(result[0].address.city+'\n');
			res.write(result[0].address.state+'\n');
			res.end();
		}, function(err) {
		    console.log(err);
    	});
});

app.get('/getAll', function (req, res) {
	db.all()
		.then(function(result) {
			//console.log(result.length + " Records:");
			//console.log(result);
/*
			for(var i=0; i<result.length; i++) {
				res.write(result[i]._id +'\n');
				res.write(result[i].name+'\n');
				res.write(result[i].email+'\n');
				res.write(result[i].address.state+'\n');
			}
			res.send();
*/
/*
			result.forEach(function(row){
				res.write(row._id+'\n');
				res.write(row.name+'\n');
				res.write(row.email+'\n');
				res.write(row.address.state+'\n\n');
			})
			res.send();
*/
			//res.writeHead(200, {"Content-Type": "application/json"});
			var people = [];
			result.forEach(function(row){
				var person = {
					id : row._id,
					name : row.name,
					email : row.email,
					address : {
						street : row.address.state
					}
				}
				people.push(person);
			})
			res.json(people);
			//res.send();
		}, function(err){
			console.log(err);
    	});
});

app.get('/deleteByName.html', function (req, res) {
   res.sendFile( __dirname + "/" + "deleteByName.html" );
})
app.get('/deleteByName', function (req, res) {
	var name = req.query.name;
	db.query({name: {'$eq': req.query.name}})
		.then(function(result) {
			db.del(result[0]._id)
	  			.then(console.log);
		}, function(err){
			console.log(err);
    	});
});

app.get('/count', function (req, res) {
	db.count()
		.then(function(result) {
			//console.log(result);
			res.send(result + " Records");
		}, function(err){
			console.log(err);
    	});
});

app.get('/get', function (req, res) {
	db.query("SELECT name, address.state FROM db WHERE address.state = 'AL'")
		.then(function(result){
			console.log(result);
			res.json(result);
		}, function(err){console.log(err);});
});


app.get('/deleteDB', function (req, res) {
	db.deleteDB().then(console.log)
});

var port = process.env.PORT || 3333
app.listen(port, function() {
    console.log("starting server on port " + port);
});