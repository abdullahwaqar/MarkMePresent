const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const AttendanceSchema = new Schema({
    class_id: { type: Schema.Types.ObjectId, required: true },
    student_id: { type: Schema.Types.ObjectId, required: true },
    class_date_time: { type: Date, default: Date.now() }
});

module.exports = Attendance = mongoose.model('attendance', AttendanceSchema);
