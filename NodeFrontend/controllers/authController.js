var express = require('express');
var router = express.Router();
var passport = require('passport');
var bodyParser = require("body-parser");

exports.login = (req, res, next) => {
    if (!req.isAuthenticated()) res.render('login', {title: 'Login'});
    else res.redirect('/');
}

exports.loginPost = (req, res, next) => {
    passport.authenticate('ldapauth', function(err, user, info) {
        if (err) {
          return next(err); // will generate a 500 error
        }
        req.login(user, { session: true }, function (err) {
          res.redirect('/');
        });
    })(req, res, next);
}

exports.logoutPost = (req, res, next) => {
    req.logout();
    res.redirect('/');
}