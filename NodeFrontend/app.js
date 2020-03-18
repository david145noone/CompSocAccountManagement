var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var passport = require('passport');
var session = require('express-session');
var bodyParser = require('body-parser');
var LdapStrategy = require('passport-ldapauth');
var expressValidator = require('express-validator');
const fs = require('fs')
var req = require('require-yml')
var conf = req('./config.yml')

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser());
app.use(session({
  secret: conf.secret,
  resave: false,
  saveUninitialized: false,
  //cookie: { secure: true }
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(express.static(path.join(__dirname, 'public')));

passport.use('ldapauth',
  new LdapStrategy({
    server: {
      url: conf.url,
      bindDn: conf.bindDn,
      bindCredentials: conf.bindCredentials,
      searchBase: conf.searchBase,
      searchFilter: conf.searchFilter,
      reconnect: true,
      tlsOptions: (true) ? {
        ca: [
          fs.readFileSync(conf.tlsCertPath)
        ]
      } : {}
    }
  })
);

passport.serializeUser(function(user,done){
  done(null,user);
});

passport.deserializeUser(function(user,done){
  done(null,user);
});

app.use('/', indexRouter);
app.use('/users', usersRouter);

app.get('/', function (req, res) {
  res.send('Hello World')
});

module.exports = app;
