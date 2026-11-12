<#include "cabecalho.ftlh">

<h2>Cadastrar livro</h2>

<#if erro??>
    <p style="color: red;">
        ${erro}
    </p>
</#if>

<form method="post" action="/livros">

    <label for="titulo">Título:</label>
    <br>
    <input type="text" id="titulo" name="titulo" value="${titulo!}">

    <br>
    <br>

    <label for="autor">Autor:</label>
    <br>
    <input type="text" id="autor" name="autor" value="${autor!}">

    <br>
    <br>

    <label for="ano">Ano:</label>
    <br>
    <input type="text" id="ano" name="ano" value="${ano!}">

    <br>
    <br>

    <label for="preco">Preço:</label>
    <br>
    <input type="text" id="preco" name="preco" value="${preco!}">

    <br>
    <br>

    <button type="submit">Cadastrar livro</button>

</form>
