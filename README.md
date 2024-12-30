# Teste-Api

Este repositório apresenta a integração com a API [ViaCEP](https://viacep.com.br/), incluindo os desafios encontrados e as soluções aplicadas durante o desenvolvimento.

## Funcionalidades
- Estruturação da URL para requisição à API.
- Requisição de dados de CEP por meio do método `GET`.
- Processamento da resposta da API para obter informações específicas, como o bairro.
- Soluções para problemas de compatibilidade com versões de bibliotecas e codificação de caracteres.

## Detalhes da Integração

### URL Base e Estrutura da Requisição
- **Método HTTP:** `GET`
- **URL Base:** `https://viacep.com.br/ws/`
- **Exemplo de construção da URL:**

```java
URL url = new URL(urlBase + cep + "/json/");
```

### Teste da API
Os testes foram realizados inicialmente no [Postman](https://www.postman.com/), mas a validação final foi feita por meio de um aplicativo Android. Para obter a resposta da API, basta acessar a URL construída.

### Exemplo de Resposta da API
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "estado": "São Paulo",
  "regiao": "Sudeste",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107"
}
```

## Estrutura do Código

### Classe `ConsultarCEP`
Responsável por:
1. Construir a URL de requisição.
2. Realizar a requisição e obter a resposta como uma string.
3. Utilizar o `BufferedReader` com codificação UTF-8 para tratar corretamente os dados recebidos.

Exemplo:
```java
BufferedReader reader = new BufferedReader(
    new InputStreamReader(connection.getInputStream(), "UTF-8")
);
```

### Classe `PegarBairro`
- Recebe a resposta gerada pela requisição.
- Converte a string em um objeto JSON.
- Filtra o objeto JSON para obter o valor do campo `bairro`.

Exemplo:
```java
JsonObject jsonObject = new JsonParser().parse(respostaJson).getAsJsonObject();
String bairro = jsonObject.get("bairro").getAsString();
```

## Desafios e Soluções

### Problema 1: Erro de Compatibilidade com a Biblioteca Gson
- **Erro:** `java.lang.nosuchmethoderror: com.google.gson.jsonparser.parsestring`.
- **Causa:** O método `parseString` não é compatível com a versão da biblioteca Gson utilizada no ambiente.
- **Solução:** Substituir o uso de `parseString` pelo método de conversão disponível na versão antiga do Gson.

```java
JsonObject jsonObject = new JsonParser().parse(respostaJson).getAsJsonObject();
```

### Problema 2: Codificação UTF-8
- **Erro:** Conflito ao salvar dados do tipo `String` devido ao uso de bytes para tratar o padrão UTF-8.
- **Solução:** Configurar explicitamente a codificação UTF-8 ao processar a resposta da API.

```java
BufferedReader reader = new BufferedReader(
    new InputStreamReader(connection.getInputStream(), "UTF-8")
);
```

## Conclusão
Este projeto demonstra a integração com a API ViaCEP e aborda os principais desafios técnicos enfrentados, como compatibilidade de bibliotecas e problemas de codificação de caracteres. As soluções apresentadas garantem uma integração funcional e confiável.

---

**Contribuições:** Sugestões e melhorias são bem-vindas. Por favor, envie um pull request ou abra uma issue.

**Licença:** Este projeto está sob a licença MIT.
