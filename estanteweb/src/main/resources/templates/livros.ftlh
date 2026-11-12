<#include "cabecalho.ftlh">

<h2>Acervo de livros</h2>

<#if livros?size == 0>

    <p>Nenhum livro cadastrado.</p>

<#else>

    <ul>

        <#list livros as livro>

            <li>

                <strong>
                    <a href="/livros/${livro.id?c}">${livro.titulo}</a>
                </strong>

                <br>

                Autor:
                ${livro.autor}

                <br>

                Ano:
                ${livro.ano}

                <br>

                Preço:
                R$ ${livro.preco}

            </li>

            <br>

        </#list>

    </ul>

</#if>