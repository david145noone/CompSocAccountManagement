var req = require('require-yml');
var axios = require('axios');
var conf = req('/root/CompSocAccountManagement/NodeFrontend/config.yml');
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
    tlsOptions: {
        host: conf.ldaphost,
        port: conf.ldapport,
        ca: [fs.readFileSync(conf.tlsCertPath)]
    }
});

module.exports = async function () {
    var res = await getMemberInfo();
    // Piping into function that queries LDAP to see there are any new members
    // Have to implement for loop as checkLDAP only takes one member at a time
    checkLDAP(res);
}

async function getMemberInfo() {
    try {
        const date = new Date();
        // Making a post request to socs website to get JSON info
        var res = await axios.post(conf.socsurl, null, { params: APIOptions });
        // Writing JSON to file as backup for 24 hours or in case we need it again
        fs.writeFile(__dirname + '/../members/'+date.getHours()+".txt", JSON.stringify(res.data.Response.data), function (err) {
            if (err) return err;
            console.log("API: Members > " + date.getHours() + ".txt");
        });
        return res.data.Response.data;
    } catch (err) {
        return err;
    }
}

function checkLDAP(data) {
    // for now we'll assume the function only recieves one member at a time
    // We're filtering by the student's ID and just returning their username but we don't need to
    var searchOptions = {
        scope: 'sub',
        filter: '(employeeNumber='+data.employeeNumber+')',
        attributes: ['uid']
    }

    // client.bind sets up for authentication
    client.bind(conf.bindDn, conf.bindCredentials, function(err) {
        if (err) console.log(err);
        else {
            // client search connects and queries LDAP
            client.search(conf.searchBase, searchOptions, function(err, res) {
                if (err) {
                    console.log('Error occurred while ldap search');
                } else {
                    res.on('searchEntry', function (entry) {
                        console.log('Entry', entry.object);
                        if (!entry.object) console.log("User Exists");
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
            });
        }
    });
}