const form = document.querySelector("#form");
const divCampos = document.querySelector("#campos");
const entityButton = document.querySelector("#abrirFormulario");
entityButton.addEventListener("click", () => gerarFormulario(entityButton.id))


const entidade = {
    epi: {
        nome: "text",
        quantidade: "number"
    },
    usuario: {
        nome: "text",
        email: "email",
        senha: "password"
    },
    emprestimo: {
        nomeUsuario: "text",
        dataDevolucao: "date"
    }
}
const gerarFormulario = (entidadeSelecionada) => {
    const campos = entidade[entidadeSelecionada];
    divCampos.innerHTML = ''

    form.action = entidadeSelecionada

    for(let campo in campos){
        divCampos.innerHTML += `
        <div>
            <label>${campo.charAt(0).toUpperCase() + campo.slice(1)}:</label>
            <input type=${campos[campo]} name=${campo}/>
        </div>
        `
    }
}

