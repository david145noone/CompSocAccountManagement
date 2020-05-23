var express = require('express');
var router = express.Router();
var authController = require('../controllers/authController');
var rootController = require('../controllers/rootController');

/*
** Naming Scheme
** Example: router.get('login'... goes to authController.login
** but router.post('login'... goes to authController.loginPost
**
** Could use this but it's not very readable
** router.route('/login').get(authController.login).post(authController.loginPost);
*/

router.get('/login', authController.login);

router.post('/login', authController.loginPost);

router.post('/logout', authController.logoutPost);

module.exports = router;
