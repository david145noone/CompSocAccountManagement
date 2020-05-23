var express = require('express');
var app = express();
var handlebars = require('express-handlebars');
var path = require('path');
var mongoose = require('mongoose');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var passport = require('passport');
var session = require('express-session');
var bodyParser = require('body-parser');
var LdapStrategy = require('passport-ldapauth');
var expressValidator = require('express-validator');
var fs = require('fs');
var req = require('require-yml');
var conf = req('./config.yml');
var schedule = require('node-schedule');

var rootRouter = require('./routes/rootRouter');
var authRouter = require('./routes/authRouter');

var rootController = require('./controllers/rootController');
var parsingController = require('./controllers/parsingController');

// Connect to database
mongoose.Promise = global.Promise;

const mongoURL = 'mongodb://'+conf.DBUSER+':'+conf.DBPASSWD+'@127.0.0.1:27017/'+conf.DB;
mongoose.connect(mongoURL, {useNewUrlParser: true, useUnifiedTopology: true, useFindAndModify: false, useCreateIndex: true});

mongoose.connection.on('error', (err) => {
  console.log('Mongoose Connection Error!', err);
});

// view engine setup
app.engine('.hbs', handlebars({defaultLayout: '../layout',extname: '.hbs'}));
app.set('view engine', '.hbs');
app.set('views', path.join(__dirname, 'views'));
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

passport.serializeUser(function(user,done) {
  done(null,user);
});

passport.deserializeUser(function(user,done) {
  done(null,user);
});

app.use('/', rootRouter);
app.use('/auth', authRouter);
app.get('*', rootController.lost); // Handling 404 Page

// Running cron job
schedule.scheduleJob('* */1 * * *', parsingController);

module.exports = app;
