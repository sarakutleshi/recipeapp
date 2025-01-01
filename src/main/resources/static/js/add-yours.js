function addIngredient() {
    const ingredientsList = document.getElementById('ingredients-list');
    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'form-control';
    input.placeholder = 'Add an ingredient';
    ingredientsList.appendChild(input);
}

function addStep() {
    const stepsList = document.getElementById('steps-list');
    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'form-control';
    input.placeholder = `Describe step ${stepsList.children.length + 1}`;
    stepsList.appendChild(input);
}

function showPreview(event) {
    const preview = document.getElementById('image-preview');
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.innerHTML = `<img src="${e.target.result}" alt="Recipe Image">`;
        };
        reader.readAsDataURL(file);
    }
}