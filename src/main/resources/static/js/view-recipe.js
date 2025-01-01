function toggleAll(source) {
    const checkboxes = document.querySelectorAll('.ingredient');
    checkboxes.forEach(checkbox => checkbox.checked = source.checked);
}
let currentIndex = 0;

function moveSlide(direction) {
    const images = document.querySelectorAll('.carousel-images .recipe-image');
    const totalImages = images.length;

    currentIndex += direction;

    if (currentIndex < 0) {
        currentIndex = totalImages - 1;
    } else if (currentIndex >= totalImages) {
        currentIndex = 0;
    }

    const offset = -currentIndex * 100; // Shift by 100% for each image
    document.querySelector('.carousel-images').style.transform = `translateX(${offset}%)`;
}