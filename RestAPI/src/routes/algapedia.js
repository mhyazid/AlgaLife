const express = require('express')
const router = express.Router()

const algapediaController = require('../controller/algapedia')

router.get('/',algapediaController.getAllAlgae)
router.get('/:id',algapediaController.getAlgaeById)

module.exports = router;