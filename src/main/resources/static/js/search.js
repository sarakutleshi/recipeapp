function searchRecipes() {
    let query = document.getElementById("searchInput").value;
    let resultsContainer = document.getElementById("searchResults");

    if (query.length === 0) {
        resultsContainer.innerHTML = ""; // Clear results if empty
        return;
    }

    fetch(`/api/recipes/search?name=${query}`)
        .then(response => response.json())
        .then(data => {
            resultsContainer.innerHTML = ""; // Clear previous results

            if (data.length === 0) {
                resultsContainer.innerHTML = "<p>No recipes found.</p>";
                return;
            }

            let list = "<ul>";
            data.forEach(recipe => {
                list += `<li><a href="/recipes/detail/${recipe.id}">${recipe.recipeName}</a></li>`;
            });
            list += "</ul>";

            resultsContainer.innerHTML = list;
        })
        .catch(error => console.error("Error fetching recipes:", error));
}
