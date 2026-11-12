<#include "cabecalho.ftlh">

<h2>Sugerir um livro</h2>

<#if erro??>
    <p style="color: red;">
        ${erro}
    </p>
</#if>

<form method="post" action="/sugestoes">

    <label for="nomeCliente">
        Seu nome:
    </label>

    <br>

    <input
        type="text"
        id="nomeCliente"
        name="nomeCliente"
        value="${nomeCliente!}"
    >

    <br>
    <br>

    <label for="livroSugerido">
        Livro sugerido:
    </label>

    <br>

    <input
        type="text"
        id="livroSugerido"
        name="livroSugerido"
        value="${livroSugerido!}"
    >

    <br>
    <br>

    <button type="submit">
        Enviar sugestão
    </button>

</form>