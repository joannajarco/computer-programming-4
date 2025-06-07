const getProducts = () => {
    return fetch("/api/products")
        .then(r => r.json());
}

const createProductHtml = (productData) => {
    const template = `
            <h4>${productData.name}</h4>
            <p>${productData.description}</p>
            <span>${productData.price}</span>
            <button data-id=$"{productData.id}">Add to cart</button>
    `;
    const liElement = document.createElement("li");
    liElement.innerHTML = template.trim();

    return liElement;
}

(() => {
    const productsListEl = document.querySelector('.productsList');
    getProducts()
        .then(products => products.map(createProductHtml))
        .then(productHtmlElements => productHtmlElements.forEach(el => productsListEl.appendChild(el)));
})();