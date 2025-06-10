const form = document.querySelector("#form");
const divCampos = document.querySelector("#campos");

const entidade = {
  epis: {
    nome: "text",
    quantidade: "number"
  },
  usuarios: {
    nome: "text",
    email: "email",
  },
  emprestimos: {
    nomeEpi: "text",
    emailUsuario: "email",
    dataDevolucao: "date"
  },
  devolucoes: {
    nomeEpi: "text",
    emailUsuario: "email",
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

  console.log(entidadeParam)
if (entidadeParam) {
  console.log(entidadeParam)
  gerarFormulario(entidadeParam);
}
