const BASE_URL = 'https://temp-markmepresent.herokuapp.com/api';

const submitBtn = document.getElementById('submitBtn');
const qrDetails = document.getElementById('qrDetails');
const card = document.getElementById('card');

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
    qrDetails.appendChild(details);

    document.getElementById('classNameInput').value = '';
    document.getElementById('teacherNameInput').value = '';

    //* Generating QRCODE
    axios.post(`${BASE_URL}/createclass`, {
        className: className,
        teacherName: teacherName
    }).then(function(response) {
        console.log(response.data)
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
