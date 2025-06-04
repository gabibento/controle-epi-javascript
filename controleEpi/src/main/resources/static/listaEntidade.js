
const params = new URLSearchParams(window.location.search);
const entidadeParam = params.get("entidade");
const listaContainer = document.querySelector("#lista");

fetch(`/${entidadeParam}`)
    .then(response => response.json())
    .then(data => {
        data.forEach(item => {
            const ul = document.createElement("ul");

        for (const chave in item) {
            const li = document.createElement("li");
            li.textContent = `${chave}: ${item[chave]}`;
                ul.appendChild(li);
            }
            listaContainer.appendChild(ul);
        });
    })
    .catch(error => {
        console.error("Erro ao buscar dados:", error);
        listaContainer.innerHTML = "<p>Erro ao carregar dados.</p>";
    });