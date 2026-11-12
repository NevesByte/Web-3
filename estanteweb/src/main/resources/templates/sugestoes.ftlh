<#include "cabecalho.ftlh">

<h2>Sugestões de leitura</h2>

<#if sugestoes?size == 0>

    <p>
        Nenhuma sugestão cadastrada.
    </p>

<#else>

    <ul>

        <#list sugestoes as sugestao>

            <li>

                <strong>
                    ${sugestao.nomeCliente}
                </strong>

                sugeriu:

                ${sugestao.livroSugerido}

            </li>

        </#list>

    </ul>

</#if>