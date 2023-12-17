const UsersModel = require('../models/users')
const bcrypt = require('bcrypt');
const SigninModel = require('../models/signin')

const userSignIn = async(req,res) => {
    const{body} = req;
    const password = await bcrypt.hash(body.password,10);

    try {
    await SigninModel.createNewUsers(body,password);
    res.status(201).json({
        message: 'CREATE new user success',
        data: [body.username, body.email]
            
        
    })
    } catch (error) {
        res.status(500).json({
            message: 'Server Error',
            serverMessage: error,
        })
    }
}

module.exports={
    userSignIn
}
