const express = require('express')

const userController = require('../controller/users')

const router = express.Router()

//Create - POST
router.post('/',)

//Read - GET
router.get('/',userController.getAllUsers)

//Update - PATCH
router.patch('/',)

//Delete -DELETE
router.delete('/',)

module.exports = router;

