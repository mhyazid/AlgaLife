const algapediaModel = require('../models/algapedia')

const getAllAlgae = async(req,res) => {
    try {
        const algae = await algapediaModel.getAllAlgae();
        res.json(algae);
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({ message: 'Internal Server Error' });
    }
}

const getAlgaeById = async(req,res) =>{
    const {id}=req.params;

    try {
        const algae = await algapediaModel.getAlgaeById(id)
        if(algae){
            res.json(algae);
        }else{
            res.status(404).json({
                message:'Algae not found'
            })
        }
    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({ message: 'Internal Server Error' });
    }
}

module.exports = {
    getAllAlgae,
    getAlgaeById
}