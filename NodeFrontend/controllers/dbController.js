var express = require('express');
var router = express.Router();
var passport = require('passport');
var bodyParser = require("body-parser");
var Member = require('../models/Member');

module.exports = {

    registerPost: (member) => {
        let newMember = new Member(member);
        newMember.save((err) => {
        if (err) console.log(err);
        });
    },

    checkDB: (member) => {
        Member.findOne({MemberID: member.MemberID}).exec((err, account) => {
            // If member doesn't exist in db return false
            if (account == undefined) return false;
            return true;
        })
    }

}
  