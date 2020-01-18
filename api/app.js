const createError = require('http-errors');
const express = require('express');
const mongoose = require('mongoose');
const logger = require('morgan');
const cors = require('cors');
const app = express();

const indexRouter = require('./routes/index');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cors({
    allowedHeaders: '*'
}));

mongoose.connect(process.env.DB_URL || require('./config/keys').DB_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true
}, function (err) {
    if (err) {
        console.error(err);
    } else {
        console.log('Database Connected');
    }
});

app.use('/api', indexRouter);

//* catch 404 and forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

//* error handler
app.use(function(err, req, res, next) {
    //* set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};
    res.status(err.status || 500).json({
        error: err
    });
});

module.exports = app;
