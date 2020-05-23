var express = require('express');
var router = express.Router();
var rootController = require('../controllers/rootController');

router.get('/', rootController.root);

module.exports = router;
