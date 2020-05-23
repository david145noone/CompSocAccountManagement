const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const memberSchema = new Schema ({
    FirstName:{
        type: String
    },
    LastName:{
        type: String
    },
    Email:{
        type: String
    },
    PhoneNumber:{
        type: String
    },
    MemberID:{
        type: String
    }
});

module.exports = mongoose.model('Member', memberSchema);