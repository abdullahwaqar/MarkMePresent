const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const AttendanceSchema = new Schema({
    class_id: { type: ObjectId },
    student_id: { type: ObjectId },
    class_date_time: { type: Date, default: Date.now() }
});

module.exports = Attendance = mongoose.model('attendance', AttendanceSchema);
