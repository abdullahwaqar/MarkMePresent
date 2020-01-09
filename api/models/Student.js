const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const StudentSchema = new Schema({
    name: { type: String },
    roll_id: { type: String },
    password: { type: String }
});

module.exports = Student = mongoose.model('student', StudentSchema);
