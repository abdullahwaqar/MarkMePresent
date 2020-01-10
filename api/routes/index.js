const express = require('express');
const router = express.Router();

const Class = require('../models/Class');
const Student = require('../models/Student');

router.post('/createclass', async function(req, res) {
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

router.post('/createstudent', async function(req, res) {
    const { body } = req;
    try {
        const newStudent = new Student({
            name: body.name,
            roll_id: body.roll_id,
            password: body.password
        });
        const response = await newStudent.save();
        res.json(response);
    } catch (error) {
        console.error(error)
    }
});

module.exports = router;
