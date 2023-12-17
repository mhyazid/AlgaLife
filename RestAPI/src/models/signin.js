const dbPool = require('../config/database')


const createNewUsers = async(body,password) =>{
    const SQLQuery = `INSERT INTO users (username, password, email) VALUES ('${body.name}','${password}','${body.email}')`;
    try {
        return await dbPool.execute(SQLQuery);
      } catch (error) {
        console.error('Error executing SQL query:', error);
        throw error;
      }

}

module.exports={
    createNewUsers
}