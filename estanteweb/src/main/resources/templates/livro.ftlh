<#include "cabecalho.ftlh">

<h2>${livro.titulo}</h2>

<p>Autor: ${livro.autor}</p>
<p>Ano: ${livro.ano}</p>
<p>Preço: R$ ${livro.preco}</p>

<#if outrosLivros?size gt 0>
    <h3>Outros livros do mesmo autor</h3>
    <ul>
        <#list outrosLivros as outroLivro>
            <li>
                <a href="/livros/${outroLivro.id?c}">${outroLivro.titulo}</a>
            </li>
        </#list>
    </ul>
</#if>

<p><a href="/livros/html">Voltar para o acervo</a></p>
