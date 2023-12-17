const dbPool = require('../config/database')

const getAllUsers = async () => {
    const SQLQuery = 'SELECT * FROM algalife.users'

    try {
        return await dbPool.execute(SQLQuery);
      } catch (error) {
        console.error('Error executing SQL query:', error);
        throw error; // Atau tambahkan penanganan kesalahan sesuai kebutuhan Anda
      }
}

module.exports = {
    getAllUsers
}
