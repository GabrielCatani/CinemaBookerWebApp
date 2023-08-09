function chooseFile() {
    let fileInput = document.getElementById('fileInput');

    fileInput.click();

    fileInput.onchange = function() {
        if (fileInput.files.length > 0) {
            uploadFile(fileInput.files);
        }
    }
}

function uploadFile(files) {
    let formData = new FormData();
    for (let i = 0; i < files.length; i++) {
        formData.append('image' + i, files[i]);
    }

    fetch('/CinemaBookingWebApp/images', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(data => {
        alert('File uploaded successfully!');
    })
    .catch(error =>{
        console.error('Error', error);
        alert('Error uploading the file: ' + error.message);
    });
}