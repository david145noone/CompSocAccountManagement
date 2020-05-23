var express = require('express');
var router = express.Router();
var adminController = require('../controllers/adminController');

router.get('/', adminController.root);

module.exports = router;