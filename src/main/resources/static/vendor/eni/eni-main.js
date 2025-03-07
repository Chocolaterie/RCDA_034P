function showErrorModal(message){
    const errorModal = UIkit.modal('#upload-error-modal');
    const errorModalMessage= document.getElementById('error-message');

    errorModalMessage.innerHTML = message;
    errorModal.show();
}

function showSuccessModal(message){
    const successModal = UIkit.modal('#upload-success-modal');
    const successModalMessage= document.getElementById('modal-success-message');

    successModalMessage.innerHTML = message;
    successModal.show();
}