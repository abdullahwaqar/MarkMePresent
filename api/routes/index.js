const express = require('express');
const router = express.Router();

const Class = require('../models/Class');

router.post('/createclass', async function(req, res, next) {
    const { body } = req;
    try {
        const newClass = new Class({
            class_name: body.className,
            teacher_name: body.teacherName
        });
        const response = await newClass.save();
        res.json(response);
    } catch (error) {
        console.error(error);
    }
});

module.exports = router;
