const BASE_URL = 'https://temp-markmepresent.herokuapp.com/api';

const submitBtn = document.getElementById('submitBtn');
const card = document.getElementById('card');
const table = document.getElementById('detail-table');

let qrCode = null;
let className = null;
let teacherName = null;

//* Add event listener to btn
submitBtn.addEventListener('click', function(event) {
    event.preventDefault()

    className = document.getElementById('classNameInput').value;
    teacherName = document.getElementById('teacherNameInput').value;

    if (!className && !teacherName) return;

    card.style.display = 'block';
    let details = document.createTextNode(`${teacherName} started class a ${className}`);

    document.getElementById('classNameInput').value = '';
    document.getElementById('teacherNameInput').value = '';

    //* Generating QRCODE
    axios.post(`${BASE_URL}/createclass`, {
        className: className,
        teacherName: teacherName
    }).then(function(response) {
        //* Add details to table
        let row = table.insertRow(1);

        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);

        cell1.innerHTML = response.data._id;
        cell2.innerHTML = response.data.class_name;
        cell3.innerHTML = response.data.teacher_name;
        cell4.innerHTML = response.data.class_date_time;

        qrCode = new QRCode('qrcode', {
            text: JSON.stringify(response.data),
            width: 200,
            height: 200,
            correctLevel : QRCode.CorrectLevel.H
        });
    }).catch(function(err) {
        console.error(err);
    });
});
