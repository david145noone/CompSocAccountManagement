var req = require('require-yml');
var axios = require('axios');
var conf = req('/root/CompSocAccountManagement/NodeFrontend/config.yml');
var dbController = require('./dbController');
var fs = require('fs');
var ldap = require('ldapjs');
var APIOptions = {
    method: conf.socspostmethod,
    username: conf.socsusername,
    password: conf.socspasswd,
    encodeOutput: true
}
var client = ldap.createClient({
    url: conf.url,
    reconnect: true,
    tlsOptions: {
        host: conf.ldaphost,
        port: conf.ldapport,
        ca: [fs.readFileSync(conf.tlsCertPath)]
    }
});

module.exports = async function () {
    // client.bind sets up for authentication
    client.bind(conf.bindDn, conf.bindCredentials, function(err) {
        if (err) console.log(err);
    });

    var res = await getMemberInfo();

    // Piping into function that queries LDAP to see there are any new members
    // Have to implement for loop as checkLDAP only takes one member at a time
    res.forEach(member => {
        var state = checkLDAP(member);
        if (!state) pushToDb(member);
        else if (state == -1) {
            console.log('ERROR IN CONNECTION');
        }
    });
}

async function getMemberInfo() {
    try {
        const date = new Date();
        // Making a post request to socs website to get JSON info
        var res = await axios.post(conf.socsurl, null, { params: APIOptions });
        // Writing JSON to file as backup for 24 hours or in case we need it again
        fs.writeFile(__dirname + '/../members/' + date.getHours() + date.getMinutes() + ".txt", JSON.stringify(res.data.Response.data), function (err) {
            if (err) return err;
            console.log("API: Members > " + date.getHours() + date.getMinutes() + ".txt");
        });
        return res.data.Response.data;
    } catch (err) {
        return err;
    }
}

function checkLDAP(member) {
    // for now we'll assume the function only recieves one member at a time
    // We're filtering by the student's ID and just returning their username but we don't need to
    var searchOptions = {
        scope: 'sub',
        filter: '(employeeNumber='+member.MemberID+')',
        attributes: ['uid']
    }

    // client search queries LDAP
    client.search(conf.searchBase, searchOptions, function(err, res) {
        if (err) {
            console.log('Error occurred while ldap search');
            return -1;
        } else {
            res.on('searchEntry', function (entry) {
                // If the member exists in ldap with that student ID, it will return true here.
                //if (entry.object) console.log(member.MemberID + " Exists");
                return true;
            });
            res.on('searchReference', function (referral) {
                console.log('Referral', referral);
            });
            res.on('error', function (err) {
                console.log('Error is', err);
            });
            // Returns long log on the connection
            /*res.on('end', function (result) {
                console.log('Result is', result);
            });*/
        }
        // Member is not in LDAP
        return false;
    });
}

function pushToDb(member) {
    // Check if user is already in DB
    if(!dbController.checkDB(member)) {
        dbController.registerPost(member);
        // Need to then go on send email to member and let them know they have an account
        // once they confirm info, push to LDAP
    }
}