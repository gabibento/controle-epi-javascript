const form = document.querySelector("#form");
const divCampos = document.querySelector("#campos");

const entidade = {
  epi: {
    nome: "text",
    validade: "date"
  },
  usuario: {
    nome: "text",
    email: "email",
  },
  emprestimo: {
    nomeUsuario: "text",
    dataDevolucao: "date"
  }
};

const gerarFormulario = (entidadeSelecionada) => {
  const campos = entidade[entidadeSelecionada];
  if (!campos) return;

  divCampos.innerHTML = '';
  form.action = entidadeSelecionada;

  for (let campo in campos) {
    console.log(campos)
    divCampos.innerHTML += `
      <div>
        <label>${campo.charAt(0).toUpperCase() + campo.slice(1)}:</label>
        <input type="${campos[campo]}" name="${campo}" required />
      </div>
    `;
  }
};

const params = new URLSearchParams(window.location.search);
const entidadeParam = params.get("entidade");

if (entidadeParam) {
  gerarFormulario(entidadeParam);
}
