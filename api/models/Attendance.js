const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const AttendanceSchema = new Schema({
    class_id: { type: ObjectId }
    
});

module.exports = Attendance = mongoose.model('attendance', AttendanceSchema);
