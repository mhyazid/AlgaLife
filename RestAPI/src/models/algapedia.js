const dbPool = require('../config/database')

const getAllAlgae = async()=>{
    const SQLQuery = 'SELECT * FROM microalgae_products';

    try {
        const [result] = await dbPool.execute(SQLQuery);
        return result
    } catch (error) {
        console.error('Error executing SQL query:', error);
        throw error;
    }
};

const getAlgaeById = async(id)=>{
    const SQLQuery = 'SELECT * FROM microalgae_products WHERE id = ?'

    try {
        const [result]=await dbPool.execute(SQLQuery,[id]);
        return result[0]
    } catch (error) {
        console.error('Error executing SQL query:', error);
        throw error;
    }
}

module.exports={
    getAllAlgae,
    getAlgaeById
}