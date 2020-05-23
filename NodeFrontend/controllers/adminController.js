var express = require('express');
var router = express.Router();
var passport = require('passport');
var bodyParser = require("body-parser");
var Member = require('../models/Member');

module.exports = {

    root: (req, res, next) => {
        if (req.isAuthenticated() && req.user.gidNumber == '500') res.render('admin', {title: 'Admin'});
        else res.redirect('/');
    }

}