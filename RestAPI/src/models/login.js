const dbPool = require('../config/database');

const getUserByUsername = async(username) =>{
    const selectQuery = 'SELECT * FROM users WHERE username = ?';

    try {
        const [result] = await dbPool.execute(selectQuery,[username]);
        return result[0];
    } catch (error) {
        console.error('Error executing SQL query:', error);
        throw error
    }
}

module.exports = {
    getUserByUsername
}