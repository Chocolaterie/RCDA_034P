document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById('file-input');
    const imagePreview = document.getElementById('image-preview');
    const previewContainer = document.getElementById('preview-container');
    const uploadForm = document.getElementById('upload-form');
    const cancelButton = document.getElementById('cancelButton');
    const progressBar = document.getElementById('js-progressbar');


    let bar = document.getElementById('js-progressbar');

    let selectedFile = null;

    UIkit.upload('.js-upload', {

        url: '', multiple: false,

        beforeSend: function (arguments) {
            console.log('beforeSend', arguments);
        }, beforeAll: function () {
            const files = arguments[1];
            selectedFile = files[0];
            previewFile(selectedFile);

            console.log('beforeAll', arguments);
            return false;
        }, load: function () {
            console.log('load', arguments);
        }, error: function () {
            console.log('error', arguments);
        }, complete: function () {
            console.log('complete', arguments);
        },

        loadStart: function (e) {
            console.log('loadStart', arguments);

            e.target.abort();

            bar.removeAttribute('hidden');
            bar.max = e.total;
            bar.value = e.loaded;
        },

        progress: function (e) {
            console.log('progress', arguments);

            bar.max = e.total;
            bar.value = e.loaded;
        },

        loadEnd: function (e) {
            console.log('loadEnd', arguments);

            bar.max = e.total;
            bar.value = e.loaded;
        },

        completeAll: function () {
            console.log('completeAll', arguments);

            setTimeout(function () {
                bar.setAttribute('hidden', 'hidden');
            }, 1000);
        },
    });

    cancelButton.addEventListener('click', function (event) {
        clearPreview()
    });

    uploadForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Empêcher l'envoi classique du formulaire

        if (!selectedFile) {
            showErrorModal("Veuillez sélectionner une image !");
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/edit-avatar", true);

        xhr.upload.onprogress = function (e) {
            if (e.lengthComputable) {
                progressBar.removeAttribute('hidden');
                progressBar.max = e.total;
                progressBar.value = e.loaded;
            }
        };

        xhr.onload = function () {
            if (xhr.status === 200) {

                showSuccessModal("L'image a été téléchargée avec succès !");

                progressBar.setAttribute('hidden', 'hidden');
            } else {
                alert("Erreur lors de l'upload !");
            }
        };

        xhr.send(formData);
    });

    function previewFile(file) {
        if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.src = e.target.result;
                previewContainer.removeAttribute('hidden'); // Afficher la prévisualisation
            };
            reader.readAsDataURL(file);
        }
    }

    // Annuler la prévisualisation
    function clearPreview() {
        selectedFile = null;
        fileInput.value = ""; // Réinitialiser l'input file
        previewContainer.setAttribute("hidden", "hidden");
    }
});