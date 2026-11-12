<!DOCTYPE html>
<html lang="pt-BR"><head><meta charset="UTF-8"><title>Contato | EstanteWeb</title></head><body>
<#include "cabecalho.ftlh">
<h2>Entre em contato</h2>
<#if erro??><p style="color:red">${erro}</p></#if>
<form method="post" action="/contato">
  <label>Nome: <input type="text" name="nome" value="${nome!}"></label><br>
  <label>Mensagem: <textarea name="mensagem"></textarea></label><br>
  <button type="submit">Enviar</button>
</form>
</body></html>
