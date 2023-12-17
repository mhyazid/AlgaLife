require('dotenv').config()
const express = require('express')
const PORT = process.env.PORT || 5000
const usersRoutes = require('./routes/users')
const signInRoutes = require('./routes/signin')
const loginRoute = require('./routes/login')
const algapediaRoute = require('./routes/algapedia')


const app = express()


app.use(express.json())
app.use('/users',usersRoutes);
app.use('/signin',signInRoutes);
app.use('/login',loginRoute);
app.use('/algapedia',algapediaRoute);



app.listen(PORT, ()=>{
    console.log(`Server berhasil di running pada port ${PORT}`)
})