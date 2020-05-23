var express = require('express');
var router = express.Router();
var passport = require('passport');
var bodyParser = require("body-parser");
var Member = require('../models/Member');

module.exports = {

  login: (req, res, next) => {
      if (!req.isAuthenticated()) res.render('login', {title: 'Login'});
      else res.redirect('/');
  },

  loginPost: (req, res, next) => {
      passport.authenticate('ldapauth', function(err, user, info) {
          if (err) {
            return next(err); // will generate a 500 error
          }
          req.login(user, { session: true }, function (err) {
            res.redirect('/');
          });
      })(req, res, next);
  },

  logoutPost: (req, res, next) => {
      req.logout();
      res.redirect('/');
  }

};
