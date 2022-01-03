# Desafio Spring1 
Primeiro desafio Spring pelo Bootcamp MELI/Digital House.
<br><br> Arquivos de configuração e payloads do postman no diretorio:
<br> src/main/resources/postman/desafio-spring-1.postman_collection.json

<table>
	<caption>Endpoints</caption>
	<thead>
	<tr>
		<th>Método</th>
		<th>Endpoint</th>
    <th>Descrição</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles</td>
        <td>Uma lista de todos os produtos disponíveis.</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles?category=categoryName</td>
        <td>Uma lista de produtos filtrados por categoria.</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles?filter1=filter1Value&filter2=filter2Value</td>
        <td>Uma lista que permite a combinação de qualquer um dos filtros. Por exemplo: categoria + frete grátis. (Todas as
combinações de 2 possíveis MENOS quantidade)</td>
	</tr>
	<tr>
		<td>GET</td>
        <td>/api/v1/articles?filter1=filter1Value&filter2=filter2Value&order=0</td>
        <td>Filtra por ordem crescente alfabetica</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles?filter1=filter1Value&filter2=filter2Value&order=1</td>
        <td>Filtra decrescente alfabetica</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles?filter1=filter1Value&filter2=filter2Value&order=2</td>
        <td>Menor preço</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/articles?filter1=filter1Value&filter2=filter2Value&order=3</td>
        <td>Maior preço</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/cart</td>
        <td>Retorna um carrinho com todos os tickets de pedidos</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/client/all</td>
        <td>Retorna todos os clientes cadastrados</td>
	</tr>
	<tr>
		<td>GET</td>
		<td>/api/v1/client/state</td>
        <td>Retorna os clientes de um determinado Estado</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/api/v1/purchase-request</td>
        <td>Adiciona tickets de pedidos com produtos</td>
	</tr>
	<tr>
		<td>POST</td>
		<td>/api/v1/insert-articles-request</td>
        <td>Salva novos produtos</td>
	</tr>
    <tr>
		<td>POST</td>
		<td>/api/v1/client/add</td>
        <td>Adiciona novos clientes</td>
	</tr>
	<tbody>
</table>

