const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')
const loginModel = require('../models/login')

const userLogin = async(req,res) => {
    const {username,password} = req.body

    try {
        const user = await loginModel.getUserByUsername(username)

        if(!user){
            return res.status(401).json({
                message: 'Invalid Credentials'
            })
        }

        const isPasswordValid = await bcrypt.compare(password, user.password)

        if(isPasswordValid){
            const secretKey = process.env.JWT_SECRET
            const token = jwt.sign({ userId: user.id }, secretKey, { expiresIn: '1h' })
            res.json({token})
        }else{
            res.status(401).json({
                message:'Invalid Credentials'
            })
        }
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({
        message: 'Internal Server Error',
        serverMessage: error,
    })
    }
}

module.exports={
    userLogin,
}