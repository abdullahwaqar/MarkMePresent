const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ClassSchema = new Schema({
    class_name: { type: String },
    teacher_name: { type: String },
    class_date_time: { type: Date, default: Date.now() }
});

module.exports = Class = mongoose.model('class', ClassSchema);
