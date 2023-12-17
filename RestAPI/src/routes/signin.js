const express = require('express')
const router = express.Router()
const signInController = require('../controller/signin')

router.post('/',signInController.userSignIn)

module.exports = router;
