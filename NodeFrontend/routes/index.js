var express = require('express');
var router = express.Router();
var passport = require('passport');
var bodyParser = require("body-parser");

router.get('/', function(req, res) {
  if (!req.isAuthenticated()) res.redirect('/login');
  else res.render('index', {title: 'Index'});
});

router.get('/login', function(req, res) {
  res.render('login', {title: 'Login'});
});

router.post('/login', function(req, res, next) {
  passport.authenticate('ldapauth', function(err, user, info) {
    if (err) {
      return next(err); // will generate a 500 error
    }
    req.login(user, { session: true }, function (err) {
      res.redirect('/');
    });
  })(req, res, next);
});

router.post('/logout', function(req, res, next) {
  req.logout();
  res.redirect('/');
});

module.exports = router;
