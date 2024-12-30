# Teste-Api
  - Localizar a como é feito a integração
  -   method = GET
  -   Url base = "https://viacep.com.br/ws/"
  -   Estruturar como seria a contrução da url, qual as informaões e as ordens que serao construidas para que seja feit a requisição.
        *    URL url = new URL(urlBase + cep + "/json/");
  -   Após determinar a estrutura, realizei um teste no site dos postman, após alguns teste e não consgui realizar o teste mas foi possivel confimar a requisção por aplicativo  android.  
  -   Apenas colocando a url com ela formada
  -   Resposta da API: {  "cep": "01001-000",  "logradouro": "Praça da Sé",  "complemento": "lado ímpar",  "unidade": "",  "bairro": "Sé",  "localidade": "São Paulo",  "uf": "SP",  "estado": "São Paulo",  "regiao": "Sudeste",  "ibge": "3550308",  "gia": "1004",  "ddd": "11",  "siafi": "7107"}
  -   Foi ultilizado a classe consultarCEP(), responsavel por estruturar a url e realizar a requisição e retorna uma string com as informações .
  -   Em seguida atraves da classe PegarBairro(), ela ira pegar a response gerado pela requisição, que foi tranformado em uma String, e transformar em um objeto json para pegar todos os objetos, e filtrar o objeto bairro e o dados presente nele.

  -   Esta etapa, nos trouxe alguns desafios o primeiro erro foi
       * used by: java.lang.nosuchmethoderror: com.google.gson.jsonparser.parsestring(ljava/lang/
         -- Metodo que estava dando esse erro acima, pois estavamos usando o parsestring que nao era compativel com a versão do Gson do sankhya versao (mais nova do Gson usa) 
         *  JsonObject jsonObject = JsonParser.parseString​(jsonString).getAsJsonObject();
         -- Foi assim que chegou a conslusao de que para resolver isso bastava usar um metodo de tranformar uma string em um obejto Json com um versão (mais antiga do gson)
          * JsonObject jsonObject = new JsonParser().parse(respostaJson).getAsJsonObject();
